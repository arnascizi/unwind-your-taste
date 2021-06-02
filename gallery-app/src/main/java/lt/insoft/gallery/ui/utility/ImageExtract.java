package lt.insoft.gallery.ui.utility;

import org.zkoss.util.media.Media;

import lt.insoft.gallery.ui.view.ImageDetails;

public final class ImageExtract {

    private ImageExtract() {}

    public static ImageDetails get(Media media) {
        ImageDetails imageDetails = new ImageDetails();
        imageDetails.setName(media.getName());
        imageDetails.setDescription(media.getFormat());
        imageDetails.setFileType(media.getContentType());
        imageDetails.setImage(media.getByteData());
        return imageDetails;
    }
}
