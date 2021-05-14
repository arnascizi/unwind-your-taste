package lt.insoft.galleryui.viewmodel;

import lombok.Getter;
import lt.insoft.gallerybl.service.ImageService;
import lt.insoft.gallerymodel.model.Image;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.io.Serializable;
import java.util.List;

public class ImageVm implements Serializable {
    private static final long serialVersionUID = -1373492169780328182L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageService imageService;

    public ImageVm(ImageService imageService) {
        this.imageService = imageService;
    }

    @Getter
    public List<Image> imageList;

    @Getter
    public Image findImage;

    @Init
    public void init() {
    }
}

