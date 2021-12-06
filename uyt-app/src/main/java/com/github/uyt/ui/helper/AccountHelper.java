package com.github.uyt.ui.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.service.UserService;
import com.github.uyt.model.UserAccount;
import com.github.uyt.ui.view.LoggedUser;
import com.github.uyt.ui.view.UserView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountHelper {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoggedUser getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return LoggedUser.builder().username(authentication.getName()).build();
        }
        return null;
    }

    public static boolean hasRole(String role) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }

    public void register(UserView userView) {
        if (userView != null) {
            userService.save(buildUserAccount(userView));
        }
    }

    public UserView getUserByName(String username) {
        return buildUserView(userService.loadUserByUsername(username));
    }

    private UserView buildUserView(UserDetails details) {
        return UserView.builder()
                .username(details.getUsername())
                .password(details.getPassword())
                .build();
    }

    private UserAccount buildUserAccount(UserView userView) {
        return UserAccount.builder()
                .id(1)
                .username(userView.getUsername())
                .password(passwordEncoder.encode(userView.getPassword()))
                .email(userView.getEmail())
                .build();
    }

}
