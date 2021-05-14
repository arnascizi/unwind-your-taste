package lt.insoft.galleryui.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"lt.insoft.gallerymodel.model"})
@EnableJpaRepositories(basePackages = {"lt.insoft.gallerybl.repository"})
public class AppConfig {}
