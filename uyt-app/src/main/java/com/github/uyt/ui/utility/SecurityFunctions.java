package com.github.uyt.ui.utility;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.uyt.ui.view.LoggedUser;

public class SecurityFunctions {

    public static boolean isUserLogged() {
        return getLoggedUser() != null;
    }

    public static LoggedUser getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return LoggedUser.builder().username(((UserDetails) principal).getUsername()).role(String.valueOf(((UserDetails) principal).getAuthorities())).build();
        }
        return null;
    }

    public static boolean hasRole(String role) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }
}
