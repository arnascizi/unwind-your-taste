package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
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
    private int currentPage = 0;

    @Getter
    private int maxPages;

    @Getter
    private String showPages;

    @Getter
    private String searchParam;

    @Init
    public void init() {
        Pageable pageable = PageRequest.of(currentPage, 8);
        imageThumbnailList = imageViewHelper.getAllImagesPageable(pageable);
    }

    @Command
    public void doSelectImage(String id) {
        Executions.sendRedirect("/image?id=" + id);
    }

    @Command
    public void doSearch() {
        imageThumbnailList = imageViewHelper.findByTagName(searchParam);
    }

    @Command
    public void doPaging() {
    }
}

