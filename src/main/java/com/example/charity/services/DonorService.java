package com.example.charity.services;

import com.example.charity.exception.ResourceNotFoundException;
import com.example.charity.dtos.DonationDto;
import com.example.charity.models.Donor;
import com.example.charity.dtos.DonorDto;
import com.example.charity.repositories.DonorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DonorService {

    private static final String NOT_FOUND = "Donor not found. id=";
    private final DonorRepository donorRepository;
    private final DonationService donationService;

    public DonorService(DonorRepository donorRepository, DonationService donationService) {
        this.donorRepository = donorRepository;
        this.donationService = donationService;
    }

    private DonorDto convertToDto(Donor donor) {
        return new DonorDto(
                donor.getId(),
                donor.getName(),
                donor.getEmail()
        );
    }

    public List<DonorDto> getAllDonors() {
        return donorRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<DonorDto> getDonorById(Long id) {
        return donorRepository.findById(id).map(this::convertToDto);
    }

    public DonorDto createDonor(DonorDto donor) {
        Donor newDonor = new Donor();
        newDonor.setName(donor.getName());
        newDonor.setEmail(donor.getEmail());
        Donor savedDonor = donorRepository.save(newDonor);
        return convertToDto(savedDonor);
    }

    public List<DonationDto> getDonationsByDonorId(Long id) {
        return donationService.findByDonorId(id);
    }

    public void deleteDonor(Long id) {
        Donor donor = donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));
        donationService.deleteAllByDonorId(donor.getId());
        donorRepository.delete(donor);
    }

    public DonorDto updateDonor(Long id, Donor donorDetails) {
        Donor donor = donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND + id));

        donor.setName(donorDetails.getName());
        donor.setEmail(donorDetails.getEmail());

        Donor updatedDonor = donorRepository.save(donor);
        return convertToDto(updatedDonor);
    }
}