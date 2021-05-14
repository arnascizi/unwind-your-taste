package lt.insoft.galleryui.helper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallerybl.service.ImageService;
import lt.insoft.gallerymodel.model.Image;
import lt.insoft.galleryui.view.ImageSmall;

@Component
@RequiredArgsConstructor
public class ImageViewHelper {

    private final ImageService imageService;

    public ImageSmall getImageView(Long id) {
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
