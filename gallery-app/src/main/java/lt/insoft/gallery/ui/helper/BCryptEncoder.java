package lt.insoft.gallery.ui.helper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptEncoder {

    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
