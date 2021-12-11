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
import javax.persistence.OneToOne;
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
@Table(name = "vartotojo_duomenys")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAccountData {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "vartotojo_duomenys_seq", sequenceName = "vartotojo_duomenys_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vartotojo_duomenys_seq")
    private Long id;

    @Column(name = "vardas")
    private String firstName;

    @Column(name = "pavarde")
    private String lastName;

    @Column(name = "sukurimo_data")
    private LocalDateTime createdAt;

    @Column(name = "atnaujinimo_data")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "lytis_id", nullable = false)
    private Gender gender;

    // TODO
    @ManyToOne(targetEntity = UserContactDetails.class, fetch = FetchType.LAZY)
    private UserContactDetails userContactDetails;

    @ManyToOne(targetEntity = UserAccount.class, fetch = FetchType.LAZY)
    private UserAccount userAccount;
}

