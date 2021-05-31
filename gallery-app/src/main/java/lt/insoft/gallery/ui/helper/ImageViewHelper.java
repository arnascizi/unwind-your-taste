package lt.insoft.gallery.ui.helper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lt.insoft.gallery.bl.service.ImageService;
import lt.insoft.gallery.model.Image;
import lt.insoft.gallery.ui.view.ImageDetails;
import lt.insoft.gallery.ui.view.ImageThumbnail;

@Component
@RequiredArgsConstructor
public class ImageViewHelper {

    private final ImageService imageService;

    public List<ImageThumbnail> getAllImagesThumbnailsView() {
        List<ImageThumbnail> listView = new ArrayList<>();
        for (Image image : imageService.fetchAllImages()) {
            listView.add(new ImageThumbnail().builder().id(image.getId()).name(image.getName()).thumbnail(image.getThumbnail()).build());
        }
        return listView;
    }

    public ImageDetails getDetailedImageView(Long id) {
        Image image = imageService.fetchImage(id);
        return image != null ? new ImageDetails().builder().id(image.getId()).name(image.getName()).fileName(image.getFileName()).description(image.getDescription()).image(image.getImage()).build() : null;
    }

    public void save(ImageDetails imageDetails) {
        imageService.saveImage(Image.builder().name(imageDetails.getName()).fileName(imageDetails.getFileName()).description(imageDetails.getDescription()).image(imageDetails.getImage()).thumbnail(imageDetails.getThumbnail()).build());
    }

    public void delete(ImageThumbnail imageThumbnail) {
        imageService.removeImage(imageThumbnail.getId());
    }

    public byte[] createThumbnail(byte[] image, String fileType) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(image); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            BufferedImage bufferedImage = ImageIO.read(inputStream);
            BufferedImage thumbnail = Scalr.resize(bufferedImage, 150);

            ImageIO.write(thumbnail, fileType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
