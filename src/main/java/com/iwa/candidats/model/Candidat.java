package com.iwa.candidats.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidat")
public class Candidat {

    public enum Etat {
        DISPONIBLE,
        INDISPONIBLE
    }

    @Id
    private String email;

    private String firstName;
    private String lastName;
    private int gender;
    private Date birthDate;
    private String citizenship;

    @Embedded
    private Adresse address;
    private String phone;
    private String photo; // URL de la photo
    private String cv; // URL du CV
    private String shortBio;

    @Enumerated(EnumType.STRING)
    private Etat etat = Etat.DISPONIBLE; // Initialisation de l'état par défaut à "Disponible"

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidat_email")
    private List<Reference> references;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidat_email")
    private List<Experience> experiences;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidat_email")
    private List<Disponibilite> disponibilites;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidat_email")
    private List<Opinion> opinions;

    // Nested static classes for Adresse, Experience, etc., go here.

    // Lombok will generate constructors, getters, and setters
}
