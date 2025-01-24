package com.example.charity.controllers;

import com.example.charity.dtos.DonationDto;
import com.example.charity.models.Donor;
import com.example.charity.dtos.DonorDto;
import com.example.charity.services.DonorService;
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
@RequestMapping(value = "/api/donors")

public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @GetMapping
    public List<DonorDto> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonorDto> getDonorById(@PathVariable Long id) {
        return donorService.getDonorById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/donations")
    public List<DonationDto> getDonationsByDonorId(@PathVariable Long id) {
        return donorService.getDonationsByDonorId(id);
    }

    @PostMapping
    public DonorDto createDonor(@RequestBody DonorDto donor) {
        return donorService.createDonor(donor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonorDto> updateDonor(@PathVariable Long id, @RequestBody Donor donorDetails) {
        return ResponseEntity.ok(donorService.updateDonor(id, donorDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return ResponseEntity.noContent().build();
    }

}
