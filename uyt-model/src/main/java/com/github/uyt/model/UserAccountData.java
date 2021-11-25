package com.github.uyt.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VARTOTOJO_DUOMENYS")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserAccountData {

    @Id
    @Column(name = "ID")
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
    private UserContactDetails userContactDetails;

    private Gender gender;



}
