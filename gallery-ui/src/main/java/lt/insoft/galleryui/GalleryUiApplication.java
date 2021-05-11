package lt.insoft.galleryui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@EntityScan(basePackages = {"lt.insoft.gallerymodel"})
@EnableJpaRepositories("lt.insoft.gallerybl")
@SpringBootApplication
public class GalleryUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleryUiApplication.class);
    }

    @Bean
    public UrlRewriteFilter getUrlRewriteFilter() {
        UrlRewriteFilter urlRewriteFilter = new UrlRewriteFilter();
        return urlRewriteFilter;
    }
}
