package lt.insoft.gallery.ui.helper;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.service.UserService;
import lt.insoft.gallery.ui.view.LoggedUser;

@Component
@RequiredArgsConstructor
public class UserViewHelper {

    private final UserService userService;

    public LoggedUser getLoggedUser() {
        Object principal = Optional.ofNullable(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication).map(Authentication::getPrincipal).orElse(null);
        if (principal instanceof LoggedUser) {
            return (LoggedUser) principal;
        }
        return null;
    }
}
