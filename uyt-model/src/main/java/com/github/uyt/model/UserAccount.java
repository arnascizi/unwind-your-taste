package com.github.uyt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VARTOTOJAS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAccount {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "vartotojas_seq", sequenceName = "VARTOTOJAS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="vartotojas_seq")
    private long userId;

    @Column(name = "PRISIJUNGIMO_VARDAS")
    private String username;

    @Column(name = "EL_PASTAS")
    private String userEmail;

    @Column(name = "SLAPTAZODIS")
    private String password;

    @Column(name = "AKTYVUOTAS")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "VARTOTOJO_GRUPE_ID", nullable = false)
    private UserRole userRole;

    @OneToMany(mappedBy = "userAccount")
    private List<Review> reviews;

    @OneToMany(mappedBy = "userAccount")
    private List<Recipe> recipeList;
}
