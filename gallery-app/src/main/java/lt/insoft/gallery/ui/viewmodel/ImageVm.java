package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;

import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import lombok.Getter;
import lombok.Setter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.view.ImageDetails;

public class ImageVm implements Serializable {
    private static final long serialVersionUID = -4894191210706198976L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    @Getter
    @Setter
    private ImageDetails imageDetails;

    @Init
    public void init(@QueryParam("id") Long id) {
        imageDetails = imageViewHelper.getDetailedImageView(27L);
    }
}
