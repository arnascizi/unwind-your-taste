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
@Table(name = "sudetingumas")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Complexity {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "sudetingumas_seq", sequenceName = "sudetingumas_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sudetingumas_seq")
    private Long id;

    @Column(name = "pavadinimas")
    private String value;

    @OneToMany(mappedBy = "complexity")
    private List<Recipe> complexRecipes;
}
