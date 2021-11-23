package com.github.uyt.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUKTAS")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PAVADINIMAS")
    private String productName;

    @Column(name = "KIEKIS")
    private BigDecimal productAmount;

    @ManyToOne
    private ProductType productType;

    @ManyToOne
    private Measurement measurement;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Recipe.class)
    private List<Recipe> recipeList;
}
