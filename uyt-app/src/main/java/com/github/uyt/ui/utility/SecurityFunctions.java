package com.github.uyt.ui.utility;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.github.uyt.ui.view.LoggedUser;

public class SecurityFunctions {

    public static boolean isUserLogged() {
        return getLoggedUser() != null;
    }

    public static LoggedUser getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return LoggedUser.builder().username(((UserDetails) principal).getUsername()).role(String.valueOf(((UserDetails) principal).getAuthorities())).build();
        } else if (principal instanceof OAuth2User) {
            return LoggedUser.builder().username(((OAuth2User) principal).getAttribute("name")).email(((OAuth2User) principal).getAttribute("email")).role(String.valueOf(((OAuth2User) principal).getAuthorities())).build();
        }
        return null;
    }

    public static boolean hasRole(String role) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }
}
