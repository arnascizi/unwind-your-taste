package com.github.uyt.ui.helper;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.service.UserService;
import com.github.uyt.model.UserAccount;
import com.github.uyt.ui.view.LoggedUser;
import com.github.uyt.ui.view.UserView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserViewHelper {

    private final UserService userService;

    public LoggedUser getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return LoggedUser.builder().username(((UserDetails) principal).getUsername()).role(String.valueOf(((UserDetails) principal).getAuthorities())).build();
        }
        return null;
    }

    public static boolean hasRole(String role) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getAuthorities().stream().anyMatch(r -> r.getAuthority().equals(role));
    }

    public UserAccount getUserAccount(UserView userView) {
        if (userView.getUsername() != null) {
            return userService.findByUsername(userView.getUsername());
        }
        return null;
    }
}
