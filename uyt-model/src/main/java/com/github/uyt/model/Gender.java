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

@Entity
@Getter
@Builder
@Table(name = "lytis")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Gender {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "lytis_seq", sequenceName = "lytis_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lytis_seq")
    private Long id;

    @Column(name = "pavadinimas")
    private String value;

    @OneToMany(mappedBy = "gender")
    private List<UserAccountData> userGenderList;
}
