package com.github.uyt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "VARTOTOJO_GRUPE")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRole {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "vartotojo_grupe_seq", sequenceName = "VARTOTOJO_GRUPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vartotojo_grupe_seq")
    private long id;

    @Column(name = "PAVADINIMAS")
    private String value;

    @OneToMany(mappedBy = "userRole")
    private List<UserAccount> userAccounts;
}
