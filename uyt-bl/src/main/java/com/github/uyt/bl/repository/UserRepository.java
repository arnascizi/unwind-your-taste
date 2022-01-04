package com.github.uyt.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.github.uyt.model.UserAccount;

@NoRepositoryBean
public interface UserRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByUsername(String username);

    String getUsernameById(Long userId);
}
