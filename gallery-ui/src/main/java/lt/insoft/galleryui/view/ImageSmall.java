package lt.insoft.galleryui.view;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ImageSmall implements Serializable {
    private static final long serialVersionUID = 803931525124255098L;

    private String name;
    private String url;
}
