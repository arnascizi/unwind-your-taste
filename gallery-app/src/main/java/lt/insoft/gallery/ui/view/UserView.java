package lt.insoft.gallery.ui.view;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lt.insoft.gallery.model.User;

@Getter
@Setter
@AllArgsConstructor
public class UserView implements Serializable {
    private final static long serialVersionUID = 497644654686714401L;

    private String username;
    private String password;

    public UserView buildFromUser(User user) {
        return new UserView(user.getUsername(), user.getPassword());
    }
}
