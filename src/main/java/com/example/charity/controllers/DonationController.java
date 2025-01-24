package com.example.charity.controllers;

import com.example.charity.models.Donation;
import com.example.charity.dtos.DonationDto;
import com.example.charity.services.DonationService;
import com.example.charity.services.ProjectService;
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
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;
    private final ProjectService projectService;

    public DonationController(DonationService donationService, ProjectService projectService) {
        this.donationService = donationService;
        this.projectService = projectService;
    }


    @GetMapping
    public List<DonationDto> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationDto> getDonationById(@PathVariable Long id) {
        return donationService.getDonationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DonationDto createDonation(@RequestBody Donation donation) {
        return projectService.createDonation(donation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonationDto> updateDonation(@PathVariable Long id, @RequestBody DonationDto donationDetails) {
        return ResponseEntity.ok(donationService.updateDonation(id, donationDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);
        return ResponseEntity.noContent().build();
    }
}
