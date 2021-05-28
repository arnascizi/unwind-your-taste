package lt.insoft.gallery.ui.view;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.insoft.gallery.model.Image;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageSmall implements Serializable {
    private static final long serialVersionUID = 803931525124255098L;

    private Long id;
    private String name;
    private byte[] file;

    public ImageSmall buildFrom(Image image) {
        return new ImageSmall(image.getId(), image.getName(), image.getFile());
    }
}
