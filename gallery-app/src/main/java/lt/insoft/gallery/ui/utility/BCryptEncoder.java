package lt.insoft.gallery.ui.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptEncoder {

    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
