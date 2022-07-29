package com.usthe.common.util;

import com.usthe.common.support.ResourceBundleUtf8Control;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * i18n ResourceBundle util
 *
 *
 */
@Slf4j
public class ResourceBundleUtil {

    private static final ResourceBundleUtf8Control BUNDLE_UTF_8_CONTROL = new ResourceBundleUtf8Control();
    private static final Integer LANG_REGION_LENGTH = 2;

    static {
        // set default locale by env
        try {
            String langEnv = System.getenv("LANG");
            if (langEnv != null) {
                String[] langArr = langEnv.split("\\.");
                if (langArr.length >= 1) {
                    String[] regionArr = langArr[0].split("_");
                    if (regionArr.length == LANG_REGION_LENGTH) {
                        String language = regionArr[0];
                        String region = regionArr[1];
                        Locale locale = new Locale(language, region);
                        Locale.setDefault(locale);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * get resource bundle by bundle name
     * @param bundleName bundle name
     * @return resource bundle
     */
    public static ResourceBundle getBundle(String bundleName) {
        try {
            return ResourceBundle.getBundle(bundleName, BUNDLE_UTF_8_CONTROL);
        } catch (MissingResourceException resourceException) {
            return ResourceBundle.getBundle(bundleName, Locale.US, BUNDLE_UTF_8_CONTROL);
        }
    }

}
