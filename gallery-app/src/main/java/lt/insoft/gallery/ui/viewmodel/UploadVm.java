package lt.insoft.gallery.ui.viewmodel;

import java.io.IOException;
import java.io.Serializable;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import lombok.Getter;
import lombok.Setter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.utility.ImageToBinary;
import lt.insoft.gallery.ui.view.ImageDetails;

public class UploadVm implements Serializable {
    private static final long serialVersionUID = 6856564609159631967L;

    @WireVariable(rewireOnActivate = true)
    private ImageViewHelper imageViewHelper;

    @Getter
    @Setter
    private ImageDetails imageDetails;

    @Init
    public void init() {
        imageDetails = new ImageDetails();
    }

    @Command
    public void doUpload(@ContextParam(ContextType.TRIGGER_EVENT)UploadEvent uploadEvent) {
        Media media = uploadEvent.getMedia();
        imageDetails = imageViewHelper.getImageDetails(media);
    }

    @Command
    public void doSave() {
        imageViewHelper.save(imageDetails);
    }
}
