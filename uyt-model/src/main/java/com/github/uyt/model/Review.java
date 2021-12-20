package com.github.uyt.model;

import java.time.LocalDateTime;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@Table(name = "komentaras")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Review {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "komentaras_seq", sequenceName = "komentaras_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "komentaras_seq")
    private Long id;

    @Column(name = "tekstas")
    private String comment;

    @Column(name = "vertinimas")
    private Integer rating;

    @Column(name = "patalpinimo_laikas")
    private LocalDateTime createdAt;

    @Column(name = "atnaujinimo_laikas")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vartotojas_id", nullable = false)
    private UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receptas_id", nullable = false)
    private Recipe recipe;
}
