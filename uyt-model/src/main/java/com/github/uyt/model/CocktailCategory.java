package com.github.uyt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "kokteilio_kategorija")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CocktailCategory {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "kokteilio_kategorija_seq", sequenceName = "kokteilio_kategorija_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kokteilio_kategorija_seq")
    private Long id;

    @Column(name = "pavadinimas")
    private String name;

    @Column(name = "aprasymas")
    private String description;

    @OneToMany(mappedBy = "cocktailCategory")
    private List<Recipe> recipeCategories;

    @ManyToOne
    @JoinColumn(name = "kategorijos_tipas_id", nullable = false)
    private CategoryType categoryType;
}

