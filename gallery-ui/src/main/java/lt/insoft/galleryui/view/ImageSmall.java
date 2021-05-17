package lt.insoft.galleryui.view;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @AllArgsConstructor
public class ImageSmall implements Serializable {
    private static final long serialVersionUID = 803931525124255098L;

    private String name;
    private String url;
    //
    // public ImageSmall buildImageSmall(Image image) {
    //     return new ImageSmall(image.getName(), image.getUrl());
    // }
}
