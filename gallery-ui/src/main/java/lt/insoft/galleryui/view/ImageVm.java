package lt.insoft.galleryui.view;

import lombok.Getter;
import lt.insoft.entity.Image;
import lt.insoft.gallerybl.service.ImageService;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.io.Serializable;
import java.util.List;

public class ImageVm implements Serializable {
    private static final long serialVersionUID = -3440818130908355085L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageService imageService;

    @Getter
    private List<Image> imageList;

    @Init
    public void init() {
        imageList = imageService.getAllImages();
    }
}
