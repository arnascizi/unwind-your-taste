package lt.insoft.gallery.ui.view;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.insoft.gallery.model.Image;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDetails implements Serializable {
    private static final long serialVersionUID = -143346528840314787L;

    private Long id;
    private String name;
    private String description;
    private LocalDateTime uploaded;
    private byte[] image;

    public ImageDetails(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ImageDetails buildFrom(Image image) {
        return new ImageDetails(image.getId(), image.getName(), image.getDescription(), image.getDate(), getImage());
    }
}
