package lt.insoft.galleryui.viewmodel;

import lt.insoft.gallerybl.service.ImageService;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.io.Serializable;

public class ImageVm implements Serializable {
    private static final long serialVersionUID = -694624904097484135L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageService imageService;

    @Init
    public void init() {
    }
}
