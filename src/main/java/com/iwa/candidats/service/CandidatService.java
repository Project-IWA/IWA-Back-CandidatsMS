package com.iwa.candidats.service;

import com.iwa.candidats.model.Candidat;
import com.iwa.candidats.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatService {

    private final CandidatRepository candidatRepository;

    @Autowired
    public CandidatService(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }

    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    public Candidat getCandidatByEmail(String email) {
        return candidatRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("Candidat not found with email: " + email));
    }

    public Candidat createOrUpdateCandidat(Candidat candidat) {
        // Assuming that email is always set in the candidat object, as it's the ID
        return candidatRepository.save(candidat);
    }

    // Delete a candidat by email
    public boolean deleteCandidatByEmail(String email) {
        if (candidatRepository.existsById(email)) {
            candidatRepository.deleteById(email);
            return true;
        }
        return false;
    }
}
