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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "SALIS")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Country {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "salis_seq", sequenceName = "SALIS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salis_seq")
    private Long id;

    @Column(name = "PAVADINIMAS")
    private String name;

    @Column(name = "SALIES_KODAS")
    private String code;

    @OneToMany(mappedBy = "country")
    private List<UserContactDetails> userContactDetails;
}
