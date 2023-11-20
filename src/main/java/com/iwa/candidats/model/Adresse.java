package com.iwa.candidats.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Adresse {
    private String streetNum;
    private String street;
    private String complement;
    private String zipCode;
    private String city;
    private String country;
}

