package lt.insoft.galleryui.viewmodel;

import java.io.Serializable;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import lombok.RequiredArgsConstructor;
import lt.insoft.galleryui.helper.ImageViewHelper;
import lt.insoft.galleryui.view.ImageSmall;

@RequiredArgsConstructor
public class ImageVm implements Serializable {
    private static final long serialVersionUID = -1373492169780328182L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    public ImageSmall imageSmall;

    @Init
    public void init() {
        imageSmall = imageViewHelper.getImageView(4534312L);
    }
}

