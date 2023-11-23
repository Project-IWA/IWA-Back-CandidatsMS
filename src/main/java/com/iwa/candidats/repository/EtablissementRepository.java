package com.iwa.candidats.repository;

import com.iwa.candidats.model.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {
    // Pas besoin de méthodes personnalisées pour le moment
}
