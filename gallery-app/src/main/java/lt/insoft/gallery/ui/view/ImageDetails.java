package lt.insoft.gallery.ui.view;

import java.io.Serializable;
import java.time.LocalDate;

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

    private String name;
    private String description;
    private LocalDate uploaded;
    private byte[] image;

}
