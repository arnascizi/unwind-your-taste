package com.github.uyt.ui.configuration;

import java.net.URL;
import java.util.Locale;

import org.zkoss.util.resource.LabelLocator;
import org.zkoss.util.resource.Labels;
import org.zkoss.util.resource.Locator;
import org.zkoss.util.resource.Locators;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppInit;

import com.github.uyt.model.PageTranslationEnum;

public class ZkInit implements WebAppInit {

    @Override
    public void init(WebApp wapp) {
        for (PageTranslationEnum translation : PageTranslationEnum.getTranslationEnums()) {
            Labels.register(new ExtraLabelLocator("./i18n/" + translation.getTranslationValue() + ".properties"));
        }
    }

    private static class ExtraLabelLocator implements LabelLocator {
        private static final String ZK_CLASS_PATH_PREFIX = ".";

        private String path;

        public ExtraLabelLocator(String path) {
            super();
            this.path = path;
        }

        @Override
        public URL locate(Locale locale) {
            String propertiesPath = path;
            final int j = propertiesPath.lastIndexOf('.');
            final String prefix = j >= 0 ? propertiesPath.substring(0, j) : propertiesPath;
            final String suffix = j >= 0 ? propertiesPath.substring(j) : "";
            propertiesPath = locale == null ? prefix + suffix : prefix + '_' + locale + suffix;

            final Locator locator = Locators.getDefault();
            return locator.getResource(propertiesPath.replace(ZK_CLASS_PATH_PREFIX, ""));
        }
    }
}
