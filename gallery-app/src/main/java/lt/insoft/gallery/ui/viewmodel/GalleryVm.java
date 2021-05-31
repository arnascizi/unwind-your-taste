package lt.insoft.gallery.ui.viewmodel;

import static org.zkoss.zk.ui.util.Clients.alert;

import java.io.Serializable;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import lombok.Getter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.view.ImageThumbnail;

public class GalleryVm implements Serializable {
    private static final long serialVersionUID = -1373492169780328182L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    @Getter
    private List<ImageThumbnail> imageThumbnailList;

    @Init
    public void init() {
        imageThumbnailList = imageViewHelper.getAllImagesThumbnailsView();
    }

    @Command
    public void doSelectImage(@BindingParam("image") ImageThumbnail imageThumbnail) {
        Messagebox.show("Are you sure you want to delete " + imageThumbnail.getName() + "?", "Question?", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, event -> {
            if (event.getName().equals("onOK")) {
                imageViewHelper.delete(imageThumbnail);
                alert("Image deleted!");
                Executions.sendRedirect("/gallery");
            } else {
                alert("Deletion canceled!");
            }
        });
    }
}

