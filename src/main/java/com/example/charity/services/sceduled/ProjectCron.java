package com.example.charity.services.sceduled;

import com.example.charity.repositories.ProjectRepository;
import com.example.charity.services.ProjectService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class ProjectCron {
    private final ProjectService projectService;

    private final ProjectRepository projectRepository;


    public ProjectCron(ProjectService projectService, ProjectRepository projectRepository) {

        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") //  00:00 every day
    public void markAsCompleted() {
        projectRepository.findByIsCompleted(false).forEach(project -> {
            Integer donationsAmountSum = projectService.getDonationSumByProjectId(project.getId());
            if (donationsAmountSum >= project.getExpectedAmount()) {
                project.setCompleted(true);
                projectRepository.save(project);
            }
        });

    }



}