package com.github.uyt.ui.helper;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.Sessions;

import com.github.uyt.bl.service.UserService;
import com.github.uyt.model.UserAccount;
import com.github.uyt.ui.view.LoggedUser;
import com.github.uyt.ui.view.UserView;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountHelper {

    private final UserService userService;

    public LoggedUser getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(Sessions.getCurrent().getAttribute("userCredential"));
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

    public void register(UserView userView) {
        if (userView != null) {
            userService.save(buildUserAccount(userView));
        }
    }

    private UserAccount buildUserAccount(UserView userView) {
        return UserAccount.builder()
                .username(userView.getUsername())
                .build();
    }

}
