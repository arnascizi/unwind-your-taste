package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import lombok.Getter;
import lombok.Setter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.view.ImageDetails;

public class UploadVm implements Serializable {
    private static final long serialVersionUID = 6856564609159631967L;

    @WireVariable(rewireOnActivate = true)
    private ImageViewHelper helper;

    @Getter
    @Setter
    private ImageDetails imageDetails;

    @Init
    public void init() {
    }

    @Command
    public void doUpload() {
        helper.saveFullImage(imageDetails);
    }
}
