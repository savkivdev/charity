package com.example.charity.controllers;

import com.example.charity.dtos.ProjectDto;
import com.example.charity.dtos.DonationDto;
import com.example.charity.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/active")
    public List<ProjectDto> getActiveProjects() {
        return projectService.getActiveProjects();
    }

    @GetMapping("/{id}/donation_sum")
    public Integer getDonationSumByProjectId(@PathVariable Long id) {
        return projectService.getDonationSumByProjectId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProjectDto createProject(@RequestBody ProjectDto project) {
        return projectService.createProject(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDetails) {
        return ResponseEntity.ok(projectService.updateProject(id, projectDetails));
    }

    @GetMapping("/{id}/donations")
    public List<DonationDto> getDonationsByProjectId(@PathVariable Long id) {
        return projectService.getDonationsByProjectId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
