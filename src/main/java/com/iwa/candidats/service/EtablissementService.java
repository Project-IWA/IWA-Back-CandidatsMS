package com.iwa.candidats.service;

import com.iwa.candidats.exception.InvalidEstablishmentDataException;
import com.iwa.candidats.model.Etablissement;
import com.iwa.candidats.repository.EtablissementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EtablissementService {

    private final EtablissementRepository etablissementRepository;

    @Autowired
    public EtablissementService(EtablissementRepository etablissementRepository) {
        this.etablissementRepository = etablissementRepository;
    }

    @Transactional
    public void validateAndSaveEstablishment(Etablissement establishment) {
        try {
            // Ajoutez ici une logique de validation pour l'établissement
            if (establishment.getEstablishmentName() == null || establishment.getEstablishmentAddress() == null) {
                throw new IllegalArgumentException("Le nom de l'établissement ou l'adresse est nulle.");
            }
            // D'autres vérifications peuvent être ajoutées ici

            // Si tout est valide, enregistrez l'établissement
            etablissementRepository.save(establishment);
        } catch (DataIntegrityViolationException e) { // Si une exception de violation de contrainte se produit, lancez une exception personnalisée
            throw new InvalidEstablishmentDataException("Une erreur s'est produite lors de la sauvegarde de l'établissement.", e);
        }
    }


    @Transactional
    public Etablissement save(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }

    // Ajoutez d'autres méthodes comme update, delete, findById si nécessaire
}
