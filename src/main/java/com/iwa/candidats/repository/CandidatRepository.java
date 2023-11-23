package com.iwa.candidats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.iwa.candidats.model.Candidat;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, String> {
    boolean existsByEmail(String email);
    // No custom methods needed for now
}
