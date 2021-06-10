package lt.insoft.gallery.ui.viewmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import lombok.Getter;
import lombok.Setter;
import lt.insoft.gallery.ui.helper.ImageViewHelper;
import lt.insoft.gallery.ui.view.ImageDetails;
import lt.insoft.gallery.ui.view.TagView;

public class UploadVm implements Serializable {
    private static final long serialVersionUID = 6856564609159631967L;

    @WireVariable(rewireOnActivate = true)
    private transient ImageViewHelper imageViewHelper;

    @Getter
    @Setter
    private ImageDetails imageDetails;

    @Getter
    private List<TagView> tags;

    @Getter
    @Setter
    private String tag;

    @Init
    public void init() {
        tags = new ArrayList<>();
        imageDetails = new ImageDetails();
    }

    @Command
    public void doUpload(@BindingParam("image") Media image) {
        if (image.getContentType().startsWith("image/")) {
            try {
                new ImageDetails();
                imageDetails = ImageDetails.builder().fileName(image.getName().substring(0, image.getName().lastIndexOf("."))).fileType(image.getContentType().substring(6)).image(image.getByteData())
                        .thumbnail(imageViewHelper.createThumbnail(image.getByteData(), image.getContentType().replace("image/", ""))).build();
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
            if(imageDetails.getName() == null || imageDetails.getName().equals("")) {
                throw new Exception("Empty");
            }
            imageDetails.setTags(tags);
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

    @Command
    @NotifyChange({"tags"})
    public void doAddTag() {
        new TagView();
        TagView newTag = TagView.builder().name("#" + tag).build();
        tags.add(newTag);
    }

    @Command
    @NotifyChange({"tags"})
    public void doRemoveTag(@BindingParam("tagName") String name) {
        tags.removeIf(selectedTag -> selectedTag.getName().equals(name));
    }
}
