package lt.insoft.gallery.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {GsonAutoConfiguration.class})
@ComponentScan(basePackages = {"lt.insoft.gallery"})
public class GalleryUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleryUiApplication.class);
    }
}
