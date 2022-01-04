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
@Table(name = "matavimo_vienetas")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Measurement {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "matavimo_vienetas_seq", sequenceName = "matavimo_vienetas_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matavimo_vienetas_seq")
    private Long id;

    @Column(name = "pavadinimas")
    private String name;

    @Column(name = "vienetas")
    private String value;

    @OneToMany(mappedBy = "measurement")
    private List<Product> productMeasurementList;
}
