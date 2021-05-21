package lt.insoft.gallery.ui.view;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageSmall implements Serializable {
    private static final long serialVersionUID = 803931525124255098L;

    private String name;
    private String url;
}
