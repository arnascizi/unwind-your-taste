package com.github.uyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SALIS")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Country {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PAVADINIMAS")
    private String countryName;

    @Column(name = "SALIES_KODAS")
    private String countryCode;
}
