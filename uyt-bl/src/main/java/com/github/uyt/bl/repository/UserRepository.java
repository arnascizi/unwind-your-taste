package com.github.uyt.bl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.uyt.model.UserAccount;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long>   {

    UserAccount findByUsername(String username);
}
