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
@Table(name = "produkto_rusis")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductType {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "produkto_rusis_seq", sequenceName = "produkto_rusis_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produkto_rusis_seq")
    private Long id;

    @Column(name = "pavadinimas")
    private String name;

    @OneToMany(mappedBy = "productType")
    private List<Product> productList;
}
