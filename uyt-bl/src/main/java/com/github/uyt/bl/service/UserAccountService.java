package com.github.uyt.bl.service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.uyt.bl.repository.UserRepository;
import com.github.uyt.bl.repository.UserRoleRepository;
import com.github.uyt.model.UserAccount;
import com.github.uyt.model.UserDetailsPrinciple;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService {

    private static final long DEFAULT_USER_ROLE_ID = 1L;

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public void save(UserAccount userAccount) {
        if (userAccount == null) {
            return;
        }
        userAccount.setUserRole(userRoleRepository.getOne(DEFAULT_USER_ROLE_ID));
        userRepository.save(userAccount);
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        UserAccount account = userRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsPrinciple(account);
    }

    public String getUsernameById(@NonNull Long userId) {
        return userRepository.getUsernameById(userId);
    }

    public UserAccount getUserAccount(@NonNull String username) {
        return userRepository.findByUsername(username);
    }
}
