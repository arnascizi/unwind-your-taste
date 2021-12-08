package com.github.uyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "KONTAKTAI")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserContactDetails {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "kontaktai_seq", sequenceName = "KONTAKTAI_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kontaktai_seq")
    private Long id;

    @Column(name = "SAVIVALDYBE")
    private String municipality;

    @Column(name = "GATVE")
    private String street;

    @Column(name = "NAMO_NR")
    private String houseNo;

    @Column(name = "BUTO_NR")
    private String apartmentNo;

    @Column(name = "PASTO_KODAS")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "SALIS_ID", nullable = false)
    private Country country;
}
