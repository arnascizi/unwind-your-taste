package lt.insoft.gallery.ui.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"lt.insoft.gallery.model"})
@EnableJpaRepositories(basePackages = {"lt.insoft.gallery.bl.repository"})
public class AppConfig {}
