package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import lombok.Getter;
import lombok.Setter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.view.ImageThumbnail;

public class GalleryVm implements Serializable {
    private static final long serialVersionUID = -1373492169780328182L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    @Getter
    private List<ImageThumbnail> imageThumbnailList;

    @Getter
    @Setter
    private int currentPage;

    @Getter
    private int maxPages;

    @Getter
    private int pageSize;

    @Getter
    @Setter
    private String searchParam;

    @Init
    @NotifyChange({"currentPage", "imageThumbnailList"})
    public void init() {
        pageSize = 8;
        maxPages = imageViewHelper.getAllImagesThumbnailsView().size();
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        imageThumbnailList = imageViewHelper.getAllImagesPageable(pageable);
    }

    @Command
    public void doSelectImage(String id) {
        Executions.sendRedirect("/image?id=" + id);
    }

    @Command
    @NotifyChange({"currentPage", "imageThumbnailList"})
    public void doPaging() {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        imageThumbnailList = imageViewHelper.getAllImagesPageable(pageable);
    }

    @Command
    @NotifyChange({"imageThumbnailList"})
    public void doSearch() {
        imageThumbnailList.clear();
        if (searchParam == null || searchParam.equals("")) {
            Pageable pageable = PageRequest.of(currentPage, pageSize);
            imageThumbnailList = imageViewHelper.getAllImagesPageable(pageable);
        } else {
            Pageable pageable = PageRequest.of(0, pageSize);
            imageThumbnailList = imageViewHelper.findImagesByName(searchParam, pageable);
        }
    }
}

