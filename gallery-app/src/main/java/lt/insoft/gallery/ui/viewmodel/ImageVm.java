package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;
import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import lombok.Getter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.view.ImageSmall;

public class ImageVm implements Serializable {
    private static final long serialVersionUID = -1373492169780328182L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    @Getter
    private List<ImageSmall> imageSmallList;

    @Init
    public void init() {
        imageSmallList = imageViewHelper.getAllImagesView();
    }
}

