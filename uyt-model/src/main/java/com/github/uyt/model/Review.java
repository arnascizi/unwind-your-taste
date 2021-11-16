package com.github.uyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "KOMENTARAS")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Review {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TEKSTAS")
    private String comment;

    @Column(name = "VERTINIMAS")
    private Double rating;
}
