package com.epam.report.portal.layers.core.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SystemPropertyUtils {
    private static final short ERROR_CODE_PARAMETER_NOT_FOUND = -1;

    public static String getOrFail(String key) {
        final String property = System.getProperty(key);
        if (property == null) {
            final String errorMessage = "A system property could not be found for key: " + key;
            log.error(errorMessage);
            System.exit(ERROR_CODE_PARAMETER_NOT_FOUND);
        }
        return property;
    }
}

