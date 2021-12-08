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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "VARTOTOJO_DUOMENYS")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAccountData {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "vartotojo_duomenys_seq", sequenceName = "VARTOTOJO_DUOMENYS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vartotojo_duomenys_seq")
    private Long id;

    @Column(name = "VARDAS")
    private String firstName;

    @Column(name = "PAVARDE")
    private String lastName;

    @Column(name = "SUKURIMO_DATA")
    private LocalDateTime createdAt;

    @Column(name = "ATNAUJINIMO_DATA")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "LYTIS_ID", nullable = false)
    private Gender gender;

    @ManyToOne(targetEntity = UserContactDetails.class, fetch = FetchType.LAZY)
    private UserContactDetails userContactDetails;

    @ManyToOne(targetEntity = UserAccount.class, fetch = FetchType.LAZY)
    private UserAccount userAccount;
}
