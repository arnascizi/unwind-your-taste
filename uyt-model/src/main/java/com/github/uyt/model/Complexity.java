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
@Table(name = "SUDETINGUMAS")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Complexity {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "sudetingumas_seq", sequenceName = "SUDETINGUMAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sudetingumas_seq")
    private long id;

    @Column(name = "PAVADINIMAS")
    private String value;

    @OneToMany(mappedBy = "complexity")
    private List<Recipe> complexRecipes;
}
