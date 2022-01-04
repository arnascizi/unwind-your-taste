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
@Table(name = "kategorijos_tipas")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryType {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "kategorijos_tipas_seq", sequenceName = "kategorijos_tipas_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kategorijos_tipas_seq")
    private Long id;

    @Column(name = "pavadinimas")
    private String value;

    @OneToMany(mappedBy = "categoryType")
    private List<CocktailCategory> cocktailCategories;
}
