package com.github.uyt.bl.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.github.uyt.model.ProductType;

@Transactional
@NoRepositoryBean
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    ProductType findProductTypeByName(String name);
}
