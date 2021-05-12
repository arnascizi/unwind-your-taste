package lt.insoft.galleryui.viewmodel;

import lombok.Getter;
import lt.insoft.gallerybl.service.ImageService;
import lt.insoft.gallerymodel.model.Image;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.io.Serializable;
import java.util.List;

public class ImageVm implements Serializable {
    private static final long serialVersionUID = -694624904097484135L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageService imageService;

    @Getter
    private List<Image> imageList;

    @Init
    public void init() {
    }
}
