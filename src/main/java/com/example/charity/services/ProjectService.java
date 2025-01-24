package com.example.charity.services;

import com.example.charity.exception.ResourceNotFoundException;
import com.example.charity.models.Donation;
import com.example.charity.dtos.DonationDto;
import com.example.charity.models.Project;
import com.example.charity.dtos.ProjectDto;
import com.example.charity.repositories.DonorRepository;
import com.example.charity.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService {
    private static final String NOT_FOUND = "Project not found. id=";
    private final DonationService donationService;
    private final ProjectRepository projectRepository;
    private final DonorRepository donorRepository;

    public ProjectService(DonationService donationService, ProjectRepository projectRepository, DonorRepository donorRepository) {
        this.donationService = donationService;
        this.projectRepository = projectRepository;
        this.donorRepository = donorRepository;
    }

    private ProjectDto convertToDto(Project project) {
        return new ProjectDto(project.getId(), project.getName(), project.getFinishDate(),  project.getDescription(), project.getExpectedAmount(), project.getCompleted());
    }

    public List<DonationDto> getDonationsByProjectId(Long projectId) {
        return donationService.findByProjectId(projectId).stream()
                .map(donation -> new DonationDto(donation.getId(), donation.getDescription(), donation.getProject(), donation.isCompleted(), donation.getDate(), donation.getDonor(), donation.getAmount()))
                .collect(Collectors.toList());
    }

    private Project convertToEntity(ProjectDto projectDto) {
        return new Project(projectDto.getId(), projectDto.getName(),  projectDto.getReleaseDate(), projectDto.getDescription(), projectDto.getExpectedAmount(), projectDto.getCompleted());
    }

    public Optional<ProjectDto> getProjectById(Long id) {
        return projectRepository.findById(id).map(this::convertToDto);
    }

    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = convertToEntity(projectDto);
        Project savedProject = projectRepository.save(project);
        return convertToDto(savedProject);
    }

    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<ProjectDto> getActiveProjects() {
        return projectRepository.findByIsCompleted(false).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Integer getDonationSumByProjectId(Long id) {
        return donationService.findByProjectId(id).stream().mapToInt(Donation::getAmount).sum();
    }

    public ProjectDto updateProject(Long id, ProjectDto projectDetails) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setFinishDate(projectDetails.getReleaseDate());
        project.setExpectedAmount(projectDetails.getExpectedAmount());
        project.setCompleted(projectDetails.getCompleted());
        Project updatedProject = projectRepository.save(project);
        return convertToDto(updatedProject);
    }

    @Transactional
    public DonationDto createDonation(Donation donation) {
        donation.setProject(projectRepository.findById(donation.getProject().getId()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + donation.getProject().getId())));
        donation.setDonor(donorRepository.findById(donation.getDonor().getId()).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + donation.getDonor().getId())));
        return donationService.createDonation(donation);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
        donationService.deleteAllByProjectId(project.getId());
        projectRepository.delete(project);
    }
}