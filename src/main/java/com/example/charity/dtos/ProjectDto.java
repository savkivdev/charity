package com.example.charity.dtos;

import java.time.LocalDate;


public class ProjectDto {
    private Long id;
    private String name;

    private String description;

    private LocalDate releaseDate;

    private Integer expectedAmount;
    private Boolean isCompleted = false;

    public Integer getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(Integer expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }


    public ProjectDto(Long id, String name, LocalDate releaseDate, String description, Integer expectedAmount, Boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.description = description;
        this.expectedAmount = expectedAmount;
        this.isCompleted = isCompleted;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
