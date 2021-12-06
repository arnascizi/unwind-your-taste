package com.github.uyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "KATEGORIJA")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PAVADINIMAS")
    private String categoryName;

    @Column(name = "APRASYMAS")
    private String categoryDescription;
}

