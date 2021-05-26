package lt.insoft.gallery.ui.view;

import java.io.Serializable;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoggedUser implements Serializable {
    private static final long serialVersionUID = 6707492216586051253L;

    @Getter
    private String username;

    public LoggedUser buildFrom(UserDetails account) {
        return new LoggedUser(account.getUsername());
    }
}
