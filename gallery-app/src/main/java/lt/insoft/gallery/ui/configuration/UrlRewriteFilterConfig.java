package lt.insoft.gallery.ui.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@Configuration
public class UrlRewriteFilterConfig {

    @Bean
    public UrlRewriteFilter getUrlRewriteFilter() {
        return new UrlRewriteFilter();
    }
}
