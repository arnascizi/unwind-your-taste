package lt.insoft.gallery.ui.view;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.insoft.gallery.model.UserAccount;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserView implements Serializable {
    private final static long serialVersionUID = 497644654686714401L;

    @NotEmpty(message = "Username can not be empty")
    private String username;

    @NotEmpty(message = "Password can not be empty")
    private String password;

    public UserView buildFromUser(UserAccount userAccount) {
        return new UserView(userAccount.getUsername(), userAccount.getPassword());
    }
}
