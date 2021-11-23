package com.github.uyt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VARTOTOJAS")
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

    @ManyToOne(targetEntity = UserAccountData.class, fetch = FetchType.LAZY)
    private UserAccountData userAccountData;
}
