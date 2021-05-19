package lt.insoft.gallery.ui.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.service.ImageService;
import lt.insoft.gallery.model.Image;
import lt.insoft.gallery.ui.view.ImageSmall;

@Component
@RequiredArgsConstructor
public class ImageViewHelper {

    private final ImageService imageService;

    public List<ImageSmall> getAllImagesView() {
        List<ImageSmall> listView = new ArrayList<>();
        for (Image image: imageService.fetchAllImages()) {
            listView.add(buildView(image));
        }
        return listView;
    }

    public ImageSmall getImageView(Long id) throws NotFoundException {
        Image image = imageService.fetchImage(id);
        return image != null ? buildView(image) : null;
    }

    private ImageSmall buildView(Image image) {
        ImageSmall view = new ImageSmall();

        view.setName(image.getName());
        view.setUrl(image.getUrl());
        return view;
    }
}
