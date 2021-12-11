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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@Table(name = "kontaktai")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserContactDetails {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "kontaktai_seq", sequenceName = "kontaktai_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kontaktai_seq")
    private Long id;

    @Column(name = "savivaldybe")
    private String municipality;

    @Column(name = "gatve")
    private String street;

    @Column(name = "namo_nr")
    private String houseNo;

    @Column(name = "buto_nr")
    private String apartmentNo;

    @Column(name = "pasto_kodas")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "salis_id", nullable = false)
    private Country country;
}
