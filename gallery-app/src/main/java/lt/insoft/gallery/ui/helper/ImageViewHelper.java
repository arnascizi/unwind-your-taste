package lt.insoft.gallery.ui.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.service.ImageService;
import lt.insoft.gallery.model.Image;
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

    public ImageSmall getImageView(Long id) {
        Image image = imageService.fetchImage(id);
        return image != null ? new ImageSmall().buildFrom(image) : null;
    }

    public void saveFullImage(ImageDetails imageDetails) {
        Image newImage = new ImageDetails().createFrom(imageDetails);
        imageService.saveImage(newImage);
    }
}
