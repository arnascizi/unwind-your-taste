package lt.insoft.gallery.ui.view;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagView implements Serializable {
    private final static long serialVersionUID = 8875876700410065880L;

    private String name;
}
