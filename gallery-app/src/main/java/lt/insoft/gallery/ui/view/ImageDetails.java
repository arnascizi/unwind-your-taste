package lt.insoft.gallery.ui.view;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDetails implements Serializable {
    private static final long serialVersionUID = -143346528840314787L;

    private Long id;
    private String name;
    private String fileName;
    private String description;
    private String fileType;
    private byte[] image;
    private byte[] thumbnail;
}
