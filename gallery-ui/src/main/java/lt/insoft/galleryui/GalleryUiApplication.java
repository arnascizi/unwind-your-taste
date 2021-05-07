package lt.insoft.galleryui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"lt.insoft.gallerymodel"})
@EnableJpaRepositories("lt.insoft.gallerybl")
@SpringBootApplication
public class GalleryUiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GalleryUiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GalleryUiApplication.class);
    }
}
