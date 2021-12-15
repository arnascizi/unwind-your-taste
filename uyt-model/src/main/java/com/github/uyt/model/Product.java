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
@Table(name = "produktas")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "produktas_seq", sequenceName = "produktas_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produktas_seq")
    private Long id;

    @Column(name = "pavadinimas")
    private String name;

    @ManyToOne
    @JoinColumn(name = "matavimo_vienetas_id", nullable = false)
    private Measurement measurement;

    @ManyToOne
    @JoinColumn(name = "produkto_rusis_id", nullable = false)
    private ProductType productType;

    @ManyToMany(mappedBy = "productList", fetch = FetchType.EAGER)
    private List<Recipe> recipeList;
}
