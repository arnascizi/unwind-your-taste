package com.github.uyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MATAVIMO_VIENETAS")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Measurement {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PAVADINIMAS")
    private String measurementName;

    @Column(name = "VIENETAS")
    private String measurementValue;
}
