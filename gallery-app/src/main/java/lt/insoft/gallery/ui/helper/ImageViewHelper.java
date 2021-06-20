package lt.insoft.gallery.ui.helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.service.ImageService;
import lt.insoft.gallery.model.Image;
import lt.insoft.gallery.model.Tag;
import lt.insoft.gallery.ui.view.ImageDetails;
import lt.insoft.gallery.ui.view.ImageThumbnail;
import lt.insoft.gallery.ui.view.TagView;

@Component
@RequiredArgsConstructor
public class ImageViewHelper {

    private final ImageService imageService;

    private final UserViewHelper userViewHelper;

    public List<ImageThumbnail> getAllImagesThumbnailsView() {
        List<ImageThumbnail> listView = new ArrayList<>();
        for (Image image : imageService.fetchAllImages()) {
            listView.add(ImageThumbnail.builder().id(image.getId()).name(image.getFileName()).thumbnail(image.getThumbnail()).build());
        }
        return listView;
    }

    public ImageDetails getDetailedImageView(Long id) {
        Image image = imageService.fetchImage(id);
        new ImageDetails();
        return ImageDetails.builder().id(image.getId()).name(image.getName()).fileName(image.getFileName()).description(image.getDescription()).image(image.getImageFile()).tags(
                tagViewList(image.getTags())).build();
    }

    public void save(ImageDetails imageDetails) {
        imageService.save(Image.builder().name(imageDetails.getName()).fileName(imageDetails.getFileName()).description(imageDetails.getDescription()).imageFile(imageDetails.getImage()).thumbnail(
                imageDetails.getThumbnail()).tags(tagList(imageDetails.getTags())).build());
    }

    public void saveWithUser(ImageDetails imageDetails) {
        imageService.save(Image.builder().name(imageDetails.getName()).fileName(imageDetails.getFileName()).description(imageDetails.getDescription()).imageFile(imageDetails.getImage()).thumbnail(
                imageDetails.getThumbnail()).tags(tagList(imageDetails.getTags())).userAccount(userViewHelper.getUserAccount(imageDetails.getUserView())).build());
    }

    public void delete(ImageDetails imageDetails) {
        imageService.removeImage(imageDetails.getId());
    }

    public byte[] createThumbnail(byte[] image, String fileType) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(image); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            BufferedImage thumbnail = Scalr.resize(bufferedImage, 150);
            ImageIO.write(thumbnail, fileType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public List<ImageThumbnail> findImagesByName(String name, Pageable pageable) {
        ArrayList<ImageThumbnail> listView = new ArrayList<>();
        for (Image image : imageService.findByName(name, pageable)) {
            listView.add(ImageThumbnail.builder().id(image.getId()).name(image.getFileName()).thumbnail(image.getThumbnail()).build());
        }
        return listView;
    }

    public List<ImageThumbnail> findImagesByNameOrTagName(String name, Pageable pageable) {
        ArrayList<ImageThumbnail> listView = new ArrayList<>();
        for (Image image : imageService.findImagesByNameOrTag(name, pageable)) {
            listView.add(ImageThumbnail.builder().id(image.getId()).name(image.getFileName()).thumbnail(image.getThumbnail()).build());
        }
        return listView;
    }

    public List<ImageThumbnail> findImagesByTagName(String name, Pageable pageable) {
        ArrayList<ImageThumbnail> listView = new ArrayList<>();
        for (Image image : imageService.findByTag(name, pageable)) {
            listView.add(ImageThumbnail.builder().id(image.getId()).name(image.getFileName()).thumbnail(image.getThumbnail()).build());
        }
        return listView;
    }

    public List<ImageThumbnail> getAllImagesPageable(Pageable pageable) {
        List<ImageThumbnail> listView = new ArrayList<>();
        for (Image image : imageService.getPageable(pageable)) {
            listView.add(ImageThumbnail.builder().id(image.getId()).name(image.getFileName()).thumbnail(image.getThumbnail()).build());
        }
        return listView;
    }

    private List<Tag> tagList(List<TagView> tagViews) {
        List<Tag> tags = new ArrayList<>();
        for (TagView tagView : tagViews) {
            tags.add(Tag.builder().name(tagView.getName()).build());
        }
        return tags;
    }

    private List<TagView> tagViewList(List<Tag> tags) {
        List<TagView> tagViews = new ArrayList<>();
        for (Tag tag : tags) {
            tagViews.add(TagView.builder().name(tag.getName()).build());
        }
        return tagViews;
    }
}
