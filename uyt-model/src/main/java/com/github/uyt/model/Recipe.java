package com.github.uyt.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "RECEPTAS")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Recipe {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "receptas_seq", sequenceName = "RECEPTAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receptas_seq")
    private Long id;

    @Column(name = "PAVADINIMAS")
    private String title;

    @Column(name = "PARUOSIMO_LAIKAS")
    private String preparationTime;

    @Column(name = "GAMINIMO_INSTRUKCIJA")
    private String preparationDescription;

    @Column(name = "PATIEKIMAS")
    private String serving;

    @Column(name = "PATALPINIMO_LAIKAS")
    private LocalDateTime createdAt;

    @Column(name = "ATNAUJINIMO_LAIKAS")
    private LocalDateTime updatedAt;

    @Column(name = "PAVEIKSLIUKAS")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "VARTOTOJAS_ID", nullable = false)
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "KATEGORIJA_ID", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "recipe")
    private List<Review> reviewList;

    @ManyToOne
    @JoinColumn(name = "SUDETINGUMAS_ID", nullable = false)
    private Complexity complexity;

    @ManyToMany
    @JoinTable(name = "SUDETIS", joinColumns = @JoinColumn(name = "RECEPTAS_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUKTAS_ID"))
    private List<Product> productList;
}

