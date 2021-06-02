package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import lombok.Getter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.view.ImageDetails;

public class UploadVm implements Serializable {
    private static final long serialVersionUID = 6856564609159631967L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    @Getter
    private ImageDetails imageDetails;

    @Wire
    private Textbox description;

    @Wire
    private Textbox imageName;

    @Init
    public void init() {
        description = new Textbox("");
        imageName = new Textbox("");
    }

    @Command
    public void doUpload(@BindingParam("image") Media image) {
        if (image.getContentType().startsWith("image/")) {
            try {
                imageDetails = new ImageDetails().builder().name(imageName.getValue()).fileName(image.getName().substring(0, image.getName().lastIndexOf("."))).description(description.getValue())
                        .fileType(image.getContentType().substring(6)).image(image.getByteData()).thumbnail(imageViewHelper.createThumbnail(image.getByteData(), image.getContentType().replace("image/", "")))
                        .build();
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
