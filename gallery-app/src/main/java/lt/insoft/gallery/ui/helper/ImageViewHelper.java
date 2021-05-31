package lt.insoft.gallery.ui.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.zkoss.util.media.Media;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.service.ImageService;
import lt.insoft.gallery.model.Image;
import lt.insoft.gallery.ui.utility.ImageExtract;
import lt.insoft.gallery.ui.view.ImageDetails;
import lt.insoft.gallery.ui.view.ImageThumbnail;

@Component
@RequiredArgsConstructor
public class ImageViewHelper {

    private final ImageService imageService;

    public List<ImageThumbnail> getAllImagesThumbnailsView() {
        List<ImageThumbnail> listView = new ArrayList<>();
        for (Image image : imageService.fetchAllImages()) {
            listView.add(new ImageThumbnail().builder().id(image.getId()).name(image.getName()).file(image.getFile()).build());
        }
        return listView;
    }

    public ImageDetails getDetailedImageView(Long id) {
        Image image = imageService.fetchImage(id);
        return image != null ? new ImageDetails().builder().id(image.getId()).name(image.getName()).description(image.getDescription()).uploaded(image.getDate()).image(image.getFile()).build() : null;
    }

    public ImageDetails getImageDetails(Media media) {
        return ImageExtract.get(media);
    }

    public void save(ImageDetails imageDetails) {
        imageService.saveImage(Image.builder().name(imageDetails.getName()).description(imageDetails.getDescription()).file(imageDetails.getImage()).build());
    }

    public void delete(ImageThumbnail imageThumbnail) {
        imageService.removeImage(imageThumbnail.getId());
    }
}
