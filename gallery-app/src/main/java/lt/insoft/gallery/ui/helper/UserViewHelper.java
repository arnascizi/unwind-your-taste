package lt.insoft.gallery.ui.helper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lt.insoft.gallery.ui.view.LoggedUser;

@Component
public class UserViewHelper {

    public LoggedUser getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return new LoggedUser().buildFrom((UserDetails) principal);
        }
        return null;
    }
}
