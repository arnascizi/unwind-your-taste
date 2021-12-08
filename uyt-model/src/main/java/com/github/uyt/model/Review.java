package com.github.uyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "KOMENTARAS")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Review {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "komentaras_seq", sequenceName = "KOMENTARAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "komentaras_seq")
    private Long id;

    @Column(name = "TEKSTAS")
    private String comment;

    @Column(name = "VERTINIMAS")
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "VARTOTOJAS_ID", nullable = false)
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "RECEPTAS_ID", nullable = false)
    private Recipe recipe;
}
