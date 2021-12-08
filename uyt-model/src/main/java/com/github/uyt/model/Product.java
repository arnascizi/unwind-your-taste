package com.github.uyt.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "PRODUKTAS")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "produktas_seq", sequenceName = "PRODUKTAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produktas_seq")
    private Long id;

    @Column(name = "PAVADINIMAS")
    private String name;

    @Column(name = "KIEKIS")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "MATAVIMO_VIENETAS_ID", nullable = false)
    private Measurement measurement;

    @ManyToOne
    @JoinColumn(name = "PRODUKTO_RUSIS_ID", nullable = false)
    private ProductType productType;

    @ManyToMany(mappedBy = "productList")
    private List<Recipe> recipeList;
}
