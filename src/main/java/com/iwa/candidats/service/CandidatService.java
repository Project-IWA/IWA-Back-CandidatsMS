package com.iwa.candidats.service;

import com.iwa.candidats.model.Candidat;
import com.iwa.candidats.model.Etablissement;
import com.iwa.candidats.model.Experience;
import com.iwa.candidats.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CandidatService {

    private final CandidatRepository candidatRepository;

    private final EtablissementService etablissementService;

    @Autowired
    public CandidatService(CandidatRepository candidatRepository, EtablissementService etablissementService) {
        this.candidatRepository = candidatRepository;
        this.etablissementService = etablissementService;
    }

    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    public Candidat getCandidatByEmail(String email) {
        return candidatRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("Candidat not found with email: " + email));
    }

    @Transactional
    public Candidat createOrUpdateCandidat(Candidat candidat) {
        // Enregistre chaque établissement avant de sauvegarder l'expérience
        for (Experience experience : candidat.getExperiences()) {
            Etablissement establishment = experience.getEstablishment();
            if (experience.getEstablishment() != null && experience.getEstablishment().getId() == null) {
                // Supposons que vous ayez un service ou un repository pour Etablissement
                etablissementService.save(establishment);
            }
        }
        return candidatRepository.save(candidat);
    }

    public Candidat updateCandidatState(String email, String etat) {
        Candidat candidat = candidatRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("Candidat not found with email: " + email));
        try {
            candidat.setEtat(Candidat.Etat.valueOf(etat.toUpperCase())); // Assurez-vous que 'etat' est un état valide
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid state: " + etat);
        }
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
