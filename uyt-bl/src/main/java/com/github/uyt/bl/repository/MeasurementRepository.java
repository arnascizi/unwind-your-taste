package com.github.uyt.bl.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.github.uyt.model.Measurement;

@NoRepositoryBean
@Transactional
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    Measurement findMeasurementByName(String name);
}
