package com.github.uyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "KONTAKTAI")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserContactDetails {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "SAVIVALDYBE")
    private String municipality;

    @Column(name = "PASTO_KODAS")
    private String postalCode;

    @Column(name = "GATVE")
    private String street;

    @Column(name = "NAMO_NR")
    private String houseNo;

    @Column(name = "BUTO_NR")
    private String apartmentNo;

    @ManyToOne
    private Country country;
}
