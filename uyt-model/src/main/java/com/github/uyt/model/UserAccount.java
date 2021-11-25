package com.github.uyt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VARTOTOJAS")
@Getter
@Immutable
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccount {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "PRISIJUNGIMO_VARDAS")
    private String username;

    @Column(name = "EL_PASTAS")
    private String email;

    @Column(name = "SLAPTAZODIS")
    private String password;

    private UserRole userRole;

    @OneToMany
    private List<Recipe> userRecipes;

    @ManyToOne(targetEntity = UserAccountData.class, fetch = FetchType.LAZY)
    private UserAccountData userAccountData;
}
