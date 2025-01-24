package com.example.charity.dtos;

import com.example.charity.models.Donation;
import com.example.charity.models.Donor;
import com.example.charity.models.Project;

import java.time.LocalDateTime;


public class DonationDto {
    private Long id;
    private String description;

    private Project project;
    private boolean isCompleted = false;
    private LocalDateTime date = LocalDateTime.now();

    private Donor donor;
    private Integer amount;

    public Donation toDonation() {
        return new Donation(
                this.id,
                this.description,
                this.project,
                this.isCompleted,
                this.date,
                this.donor,
                this.amount
        );
    }

    public DonationDto(Long id, String description, Project project, boolean isCompleted, LocalDateTime date, Donor donor, Integer amount) {
        this.id = id;
        this.description = description;
        this.project = project;
        this.isCompleted = isCompleted;
        this.date = date;
        this.donor = donor;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}