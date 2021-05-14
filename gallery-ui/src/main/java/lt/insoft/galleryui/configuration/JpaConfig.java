package lt.insoft.galleryui.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"lt.insoft.gallerybl.repository"})
public class JpaConfig {}
