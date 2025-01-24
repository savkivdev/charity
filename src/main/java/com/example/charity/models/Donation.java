package com.example.charity.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;
    private boolean isCompleted = true;
    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne()
    @JoinColumn(name = "donor_id")
    private Donor donor;
    private Integer amount;




    public Donation() {
    }

    public Donation(Long id, String description, Project project, boolean isRecent, LocalDateTime date, Donor donor, Integer amount) {
        this.id = id;
        this.description = description;
        this.project = project;
        this.isCompleted = isRecent;
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

    public void setDescription(String name) {
        this.description = name;
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

    public void setDate(LocalDateTime createdDate) {
        this.date = createdDate;
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

    public void setAmount(Integer link) {
        this.amount = link;
    }

}