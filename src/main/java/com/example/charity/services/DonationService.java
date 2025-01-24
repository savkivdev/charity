package com.example.charity.services;

import com.example.charity.exception.ResourceNotFoundException;
import com.example.charity.models.Donation;
import com.example.charity.dtos.DonationDto;
import com.example.charity.repositories.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DonationService {

    private static final String NOT_FOUND = "Donation not found. id=";
    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    private DonationDto convertToDto(Donation donation) {
        return new DonationDto(
                donation.getId(),
                donation.getDescription(),
                donation.getProject(),
                donation.isCompleted(),
                donation.getDate(),
                donation.getDonor(),
                donation.getAmount()
        );
    }


    public Optional<DonationDto> getDonationById(Long id) {
        return donationRepository.findById(id).map(this::convertToDto);
    }

    public List<Donation> findByProjectId(Long projectId) {
        return donationRepository.findByProjectId(projectId);
    }


    public List<DonationDto> getAllDonations() {
        return donationRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    public DonationDto createDonation(Donation donation) {
        Donation savedDonation = donationRepository.save(donation);
        return convertToDto(savedDonation);
    }

    public void deleteDonation(Long id) {
        Donation donation = donationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
        donationRepository.delete(donation);
    }

    public List<DonationDto> findByDonorId(Long id) {
        return donationRepository.findByDonorId(id).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public DonationDto updateDonation(Long id, DonationDto donationDetails) {
        Donation donation = donationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));

        donation.setDescription(donationDetails.getDescription());
        donation.setProject(donationDetails.getProject());
        donation.setDonor(donationDetails.getDonor());
        donation.setAmount(donationDetails.getAmount());
        donation.setCompleted(donationDetails.isCompleted());
        donation.setDate(donationDetails.getDate());
        Donation updatedDonation = donationRepository.save(donation);
        return convertToDto(updatedDonation);
    }

    public void deleteAllByProjectId(Long projectId) {
        donationRepository.deleteAllByProjectId(projectId);
    }

    public void deleteAllByDonorId(Long id) {
        donationRepository.deleteAllByDonorId(id);
    }
}