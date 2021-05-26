package lt.insoft.gallery.ui.view;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDetails implements Serializable {
    private static final long serialVersionUID = -143346528840314787L;

    private String name;
    private String description;
    private LocalDateTime uploaded;
    private byte[] image;

    public ImageDetails(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
