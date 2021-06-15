package lt.insoft.gallery.bl.exception;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(Long id) {
        super("Such image with id " + id +" is not found!");
    }
}
