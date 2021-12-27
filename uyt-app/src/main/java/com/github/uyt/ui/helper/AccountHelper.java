package com.github.uyt.ui.helper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.service.UserAccountService;
import com.github.uyt.model.UserAccount;
import com.github.uyt.ui.view.UserView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountHelper {

    private final UserAccountService userAccountService;
    private final BCryptPasswordEncoder passwordEncoder;

    public void register(UserView userView) {
        if (userView != null) {
            userAccountService.save(buildUserAccount(userView));
        }
    }

    public UserView getUserByName(String username) {
        return buildUserView(userAccountService.loadUserByUsername(username));
    }

    private UserView buildUserView(UserDetails details) {
        return UserView.builder()
                .username(details.getUsername())
                .password(details.getPassword())
                .build();
    }

    private UserAccount buildUserAccount(UserView userView) {
        return UserAccount.builder()
                .username(userView.getUsername())
                .password(passwordEncoder.encode(userView.getPassword()))
                .userEmail(userView.getEmail())
                .build();
    }

}
