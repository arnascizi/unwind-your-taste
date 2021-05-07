package lt.insoft.galleryui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"lt.insoft.entity"})
@EnableJpaRepositories("lt.insoft.gallerybl")
@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class)
public class GalleryUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleryUiApplication.class, args);
    }

}
