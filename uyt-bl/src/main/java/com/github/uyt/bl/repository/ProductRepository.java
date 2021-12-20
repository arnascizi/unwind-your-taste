package com.github.uyt.bl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.github.uyt.model.Product;

@Transactional
@NoRepositoryBean
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getAllProducts();
}
