package lt.insoft.gallery.bl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;

import lt.insoft.gallery.bl.service.ImageService;
import lt.insoft.gallery.model.Image;

public class Main {

    private static ImageService imageService;

    @Autowired
    public Main(ImageService imageService) {
        this.imageService = imageService;
    }

    public static void main(String[] args) throws IOException {

        Image image = new Image("Waterfall", java.time.LocalDateTime.now(), "A short description", convertToBinary("C:/gallery/waterfall.jpg"));

        imageService.saveImage(image);

    }

    public static byte[] convertToBinary(String source) throws IOException {
        return Files.readAllBytes(Paths.get(source));
    }
}
