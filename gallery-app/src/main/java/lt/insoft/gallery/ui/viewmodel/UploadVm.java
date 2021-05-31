package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.exolab.castor.types.DateTime;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import lombok.Getter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.view.ImageDetails;

public class UploadVm implements Serializable {
    private static final long serialVersionUID = 6856564609159631967L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    @Getter
    private ImageDetails imageDetails;

    @Init
    public void init() {
    }

    @Command
    public void doUpload(@BindingParam("image") Media image) {
        if (image.getContentType().contains("image")) {
            try {
                imageDetails = new ImageDetails().builder().name(image.getName().substring(0, image.getName().lastIndexOf("."))).fileName(
                        image.getName().substring(0, image.getName().lastIndexOf("."))).description(image.getContentType()).uploaded(LocalDateTime.now()).image(image.getByteData()).thumbnail(
                        imageViewHelper.createThumbnail(image.getByteData(), image.getContentType().replace("image/", ""))).build();
            } catch (Exception e) {
                Messagebox.show(e.toString());
            }
        } else {
            Messagebox.show("Wrong file type", "Warning", Messagebox.OK, Messagebox.ERROR);
        }
    }

    @Command
    public void doSave() {
        try {
            imageViewHelper.save(imageDetails);
            Messagebox.show("Image was successfully saved!", "Information", Messagebox.OK, Messagebox.INFORMATION, event -> {
                if (event.getName().equals("onOK")) {
                    Executions.sendRedirect("/gallery");
                }
            });
        } catch (Exception e) {
            Messagebox.show("Unable to save the image!", "Warning", Messagebox.OK, Messagebox.ERROR);
        }
    }
}
