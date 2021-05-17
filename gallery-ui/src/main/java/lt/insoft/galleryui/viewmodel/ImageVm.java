package lt.insoft.galleryui.viewmodel;

import java.io.Serializable;
import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import javassist.NotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lt.insoft.galleryui.helper.ImageViewHelper;
import lt.insoft.galleryui.view.ImageSmall;

@RequiredArgsConstructor
public class ImageVm implements Serializable {
    private static final long serialVersionUID = -1373492169780328182L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    @Getter
    public ImageSmall imageSmall;

    @Getter
    public List<ImageSmall> imageSmallList;

    @Init
    public void init() throws NotFoundException {
        imageSmall = imageViewHelper.getImageView(1L);
        imageSmallList = imageViewHelper.getAllImagesView();
    }
}

