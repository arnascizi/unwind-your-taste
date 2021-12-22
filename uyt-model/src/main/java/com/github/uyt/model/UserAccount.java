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

import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "vartotojas")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAccount {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "vartotojas_seq", sequenceName = "vartotojas_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="vartotojas_seq")
    private Long id;

    @Column(name = "prisijungimo_vardas")
    private String username;

    @Column(name = "el_pastas")
    private String userEmail;

    @Column(name = "slaptazodis")
    private String password;

    @Column(name = "aktyvuotas")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "vartotojo_grupe_id", nullable = false)
    private UserRole userRole;

    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @OneToMany(mappedBy = "userAccount")
    private List<Recipe> recipeList;
}



