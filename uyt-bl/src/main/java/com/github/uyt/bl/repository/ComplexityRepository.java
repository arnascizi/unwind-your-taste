package com.github.uyt.bl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.github.uyt.model.Complexity;

@Transactional
@NoRepositoryBean
public interface ComplexityRepository extends JpaRepository<Complexity, Long> {

    List<Complexity> fetchAllComplexities();
}
