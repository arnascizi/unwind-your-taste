package lt.insoft.gallery.ui.view;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LoggedUser implements Serializable {
    private static final long serialVersionUID = 6707492216586051253L;

    @Getter
    private String username;

    @Getter
    private String role;
}
