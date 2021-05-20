package lt.insoft.gallery.ui.view;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lt.insoft.gallery.model.Image;

@Getter
@Setter
@RequiredArgsConstructor
public class ImageSmall implements Serializable {
    private static final long serialVersionUID = 803931525124255098L;

    private final String name;
    private final byte[] file;

    public ImageSmall buildFromImage(Image image) {
        return new ImageSmall(image.getName(), image.getFile());
    }
}
