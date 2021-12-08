package com.github.uyt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name = "MATAVIMO_VIENETAS")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Measurement {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "matavimo_vienetas_seq", sequenceName = "MATAVIMO_VIENETAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matavimo_vienetas_seq")
    private long id;

    @Column(name = "PAVADINIMAS")
    private String name;

    @Column(name = "VIENETAS")
    private String value;

    @OneToMany(mappedBy = "measurement")
    private List<Product> productMeasurementList;
}
