package com.epam.report.portal.config;

import com.epam.report.portal.utils.SystemPropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

@Slf4j
public class AppConfiguration {
    private static PropertiesConfiguration properties;

    private AppConfiguration() {
    }

    public static void setReportPortalProperties() {
        AppConfiguration prop = new AppConfiguration();
        prop.setPropertyFile();
    }

    private static String getProperties(String name) {
        return properties.getProperty(name).toString();
    }

    private static void setProperties(String propertyName, String propertyValue) {
         properties.setProperty(propertyName, propertyValue);
    }

    private void setPropertyFile() {
        try {
            log.info("Initializing property file");
            properties = new PropertiesConfiguration(getClass().getResource("/" + "report-portal.properties"));
            log.info("Property file initialization {} was done", getClass().getResource("/" + "report-portal.properties"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static String getAppName() {
        return getProperties("application.name");
    }

    public static String getReportPortalUrl() {
        return getProperties("reportPortal.url");
    }

    public static String getReportPortalProjectName() {
        return getProperties("reportPortal.project.name");
    }

    public static String getUserName() {
        String userName = SystemPropertyUtils.getOrFail("rp.user.name");
        log.info("user name is {}", userName);
        return userName;
    }

    public static String getUserPassword() {
        String userPassword = SystemPropertyUtils.getOrFail("rp.user.password");
        log.info("user password is {}", userPassword);
        return userPassword;
    }

    public static String getDefaultLogLever() {
        return getProperties("log.level");
    }

    public static String getLoggingTool() {
        return getProperties("logging.tool");
    }

    public static String getReportTool() {
        return getProperties("report.tool");
    }

    public static void setBearerToken(String token) {
        setProperties("bearer.token", "bearer " + token);
    }

    public static String getBearerToken() {
        return getProperties("bearer.token");
    }
}
