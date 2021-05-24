package lt.insoft.gallery.ui.view;

import java.io.Serializable;

import lombok.Getter;

public class LoggedUser implements Serializable {
    private static final long serialVersionUID = 6707492216586051253L;

    @Getter
    private Long id;
    @Getter
    private String username;
}
