package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import lombok.Getter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.view.ImageThumbnail;

public class GalleryVm implements Serializable {
    private static final long serialVersionUID = -1373492169780328182L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    @Getter
    private List<ImageThumbnail> imageThumbnailList;

    @Getter
    private int currentPage;

    @Getter
    private int maxPages;

    @Getter
    private String showPages;

    @Init
    public void init() {
        imageThumbnailList = imageViewHelper.getAllImagesThumbnailsView();
    }

    @Command
    public void doSelectImage(String id) {
        Executions.sendRedirect("/image?id=" + id);
    }

    @Command
    public void doPreviousPage() {
    }

    @Command
    public void doNextPage() {

    }
}

