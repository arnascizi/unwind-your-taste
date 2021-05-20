package lt.insoft.gallery.ui.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageToBinary {

    public static byte[] convertToBinary(String source) throws IOException {
        return Files.readAllBytes(Paths.get(source));
    }
}
