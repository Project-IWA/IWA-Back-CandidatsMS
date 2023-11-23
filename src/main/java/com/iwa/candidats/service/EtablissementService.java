package com.iwa.candidats.service;

import com.iwa.candidats.model.Etablissement;
import com.iwa.candidats.repository.EtablissementRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Etablissement save(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }

    // Ajoutez d'autres méthodes comme update, delete, findById si nécessaire
}
