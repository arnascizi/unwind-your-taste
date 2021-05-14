package lt.insoft.galleryui.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"lt.insoft.gallerymodel.model"})
public class EntityConfig {
}
