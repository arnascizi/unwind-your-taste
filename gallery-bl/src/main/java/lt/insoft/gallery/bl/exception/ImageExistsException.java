package lt.insoft.gallery.bl.exception;

public class ImageExistsException extends RuntimeException {
    public ImageExistsException(Long id) {
        super("Such image with id " + id +" already exist!");
    }
}
