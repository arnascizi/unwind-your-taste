package com.github.uyt.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.uyt.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {}
