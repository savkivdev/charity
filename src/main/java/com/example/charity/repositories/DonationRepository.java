package com.example.charity.repositories;

import com.example.charity.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    void deleteAllByProjectId(Long projectId);

    List<Donation> findByProjectId(Long projectId);

    void deleteAllByDonorId(Long id);

    List<Donation> findByDonorId(Long id);
}