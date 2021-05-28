package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import lombok.Getter;
import lombok.Setter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.view.ImageDetails;

public class UploadVm implements Serializable {
    private static final long serialVersionUID = 6856564609159631967L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    @Getter
    @Setter
    private ImageDetails imageDetails;

    @Getter
    @Setter
    private boolean uploadSuccessful;

    @Init
    public void init() {
        imageDetails = new ImageDetails();
    }

    @Command
    public void doUpload(@BindingParam("image") Media image) {
        try {
            imageDetails.setName(image.getName());
            imageDetails.setDescription(image.getFormat());
            imageDetails.setUploaded(LocalDateTime.now());
            imageDetails.setImage(image.getByteData());
        } catch (Exception e) {
            Messagebox.show(e.toString());
        }
    }

    @Command
    public void doSave() {
        try {
            imageViewHelper.save(imageDetails);
            Clients.alert("Success!", "Success!", "INFORMATION");
        } catch (Exception e) {
            Clients.alert("Failed!", "Failed","ERROR");
        }
    }
}
