package com.github.uyt.model;

import java.time.LocalDateTime;
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
@Table(name = "RECEPTAS")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Recipe {

    @Id
    @Column(name = "ID")
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
    //
    // private User user;
    //
    // private Type type;

    @ManyToOne
    private Category category;
    //
    private Complexity complexity;
    //
    // private List<Review> reviews;

    @ManyToMany(targetEntity = Product.class, fetch = FetchType.LAZY, mappedBy = "SUDETIS")
    private List<Product> productList;
}
