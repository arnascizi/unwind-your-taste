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
import lt.insoft.gallery.ui.view.ImageSmall;

@Component
@RequiredArgsConstructor
public class ImageViewHelper {

    private final ImageService imageService;

    public List<ImageSmall> getAllImagesView() {
        List<ImageSmall> listView = new ArrayList<>();
        for (Image image : imageService.fetchAllImages()) {
            listView.add(new ImageSmall().buildFrom(image));
        }
        return listView;
    }

    public ImageDetails getImageView(Long id) {
        Image image = imageService.fetchImage(id);
        return image != null ? new ImageDetails().buildFrom(image) : null;
    }

    public ImageDetails getImageDetails(Media media ) {
        return ImageExtract.get(media);
    }

    public void save(ImageDetails imageDetails) {
        imageService.saveImage(buildImage(imageDetails));
    }

    public void delete(ImageSmall imageSmall) {
        imageService.removeImage(imageSmall.getId());
    }

    private Image buildImage(ImageDetails imageDetails) {
        Image result = new Image();
        result.setName(imageDetails.getName());
        result.setDescription(imageDetails.getDescription());
        result.setFile(imageDetails.getImage());
        return result;
    }

}
