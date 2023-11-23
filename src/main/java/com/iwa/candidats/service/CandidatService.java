package com.iwa.candidats.service;

import com.iwa.candidats.exception.*;
import com.iwa.candidats.model.Candidat;
import com.iwa.candidats.model.Etablissement;
import com.iwa.candidats.model.Experience;
import com.iwa.candidats.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            return candidatRepository.findAll();
        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Impossible d'accéder à la base de données pour récupérer les candidats.", e);
        }
    }

    public Candidat getCandidatByEmail(String email) {
        return candidatRepository.findById(email)
                .orElseThrow(() -> new CandidatNotFoundException(email));
    }

    @Transactional
    public Candidat createOrUpdateCandidat(Candidat candidat) {
        // Vérifier si le candidat existe déjà lors de la création
        if (candidat.getEmail() == null && candidatRepository.existsByEmail(candidat.getEmail())) {
            throw new CandidatAlreadyExistsException("Un candidat avec l'email " + candidat.getEmail() + " existe déjà.");
        }

        // Enregistre chaque établissement avant de sauvegarder l'expérience
        for (Experience experience : candidat.getExperiences()) {
            Etablissement establishment = experience.getEstablishment();
            if (establishment != null && establishment.getId() == null) {
                // Vérifier si l'établissement existe déjà ou si des données sont manquantes
                etablissementService.validateAndSaveEstablishment(establishment);
            }
        }

        try {
            return candidatRepository.save(candidat);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidEstablishmentDataException("Une erreur s'est produite lors de la sauvegarde du candidat.", e);
        }
    }

    public Candidat updateCandidatState(String email, String etat) {
        Candidat candidat = candidatRepository.findById(email)
                .orElseThrow(() -> new CandidatNotFoundException(email));
        try {
            candidat.setEtat(Candidat.Etat.valueOf(etat.toUpperCase())); // Assurez-vous que 'etat' est un état valide
        } catch (IllegalArgumentException e) {
            throw new InvalidStateException(etat);
        }
        return candidatRepository.save(candidat);
    }

    // Delete a candidat by email
    public void deleteCandidatByEmail(String email) {
        if (candidatRepository.existsById(email)) {
            try {
                candidatRepository.deleteById(email);
            } catch (Exception e) {
                // Vous pouvez logger l'exception e si nécessaire
                throw new CandidatDeletionException(email);
            }
        } else {
            throw new CandidatNotFoundException(email);
        }
    }

}
