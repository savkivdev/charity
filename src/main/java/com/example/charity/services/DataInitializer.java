package com.example.charity.services;

import com.example.charity.models.Project;
import com.example.charity.models.Donation;
import com.example.charity.models.Donor;
import com.example.charity.repositories.ProjectRepository;
import com.example.charity.repositories.DonationRepository;
import com.example.charity.repositories.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProjectRepository projectRepository;
    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;

    @Autowired
    public DataInitializer(ProjectRepository projectRepository, DonationRepository donationRepository, DonorRepository donorRepository) {
        this.projectRepository = projectRepository;
        this.donationRepository = donationRepository;
        this.donorRepository = donorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        for (int i = 0; i < 12 + random.nextInt(5); i++) {
            Project project = new Project();
            project.setName("Project " + i);
            project.setDescription("Description for project " + i);
            project.setFinishDate(LocalDate.now().plusMonths(1));
            project.setExpectedAmount(1000 + random.nextInt(9000));
            project.setCompleted(false);
            projectRepository.save(project);
        }


        for (int i = 0; i < 9; i++) {
            Donor donor = new Donor();
            donor.setName("Donor " + i);
            donor.setEmail("donor" + i + "@example.com");
            donorRepository.save(donor);
        }


        for (int i = 0; i < 6; i++) {
            Donation donation = new Donation();
            donation.setDescription("Donation " + i);
            donation.setDate(LocalDateTime.now());
            donation.setAmount(100 + random.nextInt(900));
            donation.setCompleted(true);
            donation.setProject(projectRepository.findAll().get(random.nextInt((int) projectRepository.count())));
            donation.setDonor(donorRepository.findAll().get(random.nextInt((int) donorRepository.count())));
            donationRepository.save(donation);
        }
    }
}