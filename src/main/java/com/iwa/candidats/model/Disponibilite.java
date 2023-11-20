package com.iwa.candidats.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "disponibilite")
public class Disponibilite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobCategory;
    private Date startsAt;
    private Date endsAt;

    @ElementCollection
    @CollectionTable(name = "disponibilite_places", joinColumns = @JoinColumn(name = "disponibilite_id"))
    @Column(name = "place")
    private List<String> places;
}
