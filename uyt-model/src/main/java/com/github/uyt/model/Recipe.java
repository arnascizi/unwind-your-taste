package com.github.uyt.model;

import java.time.LocalDateTime;
import java.util.Arrays;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "receptas")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Recipe {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "receptas_seq", sequenceName = "receptas_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receptas_seq")
    private Long id;

    @Column(name = "pavadinimas")
    private String title;

    @Column(name = "gaminimo_instrukcija")
    private String preparationDescription;

    @Column(name = "patiekimas")
    private String serving;

    @Column(name = "patalpinimo_laikas")
    private LocalDateTime createdAt;

    @Column(name = "atnaujinimo_laikas")
    private LocalDateTime updatedAt;

    @Column(name = "paveiksliukas")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "vartotojas_id", nullable = false)
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "kokteilio_kategorija_id", nullable = false)
    private CocktailCategory cocktailCategory;

    @OneToMany(mappedBy = "recipe")
    private List<Review> reviewList;

    @ManyToOne
    @JoinColumn(name = "sudetingumas_id", nullable = false)
    private Complexity complexity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sudetis", joinColumns = @JoinColumn(name = "receptas_id"), inverseJoinColumns = @JoinColumn(name = "produktas_id"))
    private List<Product> productList;

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", preparationDescription='" + preparationDescription + '\'' +
                ", serving='" + serving + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", image=" + Arrays.toString(image) +
                ", userAccount=" + userAccount +
                ", cocktailCategory=" + cocktailCategory +
                ", reviewList=" + reviewList +
                ", complexity=" + complexity +
                ", productList=" + productList +
                '}';
    }
}

