package com.github.uyt.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.github.uyt.model.UserRole;

@NoRepositoryBean
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findUserRoleByValue(String userRole);
}
