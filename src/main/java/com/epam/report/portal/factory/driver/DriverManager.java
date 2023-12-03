package com.epam.report.portal.factory.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public class DriverManager {

    private static final ThreadLocal<WebDriver> WEBDRIVER_POOL = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (WEBDRIVER_POOL.get() == null) {
            log.info("set driver to poll");
            WEBDRIVER_POOL.set(DriverFactory.createDriver("chrome"));
        }
        return WEBDRIVER_POOL.get();
    }

    public static void quitDriver() {
        if (WEBDRIVER_POOL.get() != null) {
            log.info("remove driver from poll");
            WEBDRIVER_POOL.get().quit();
            WEBDRIVER_POOL.set(null);
        }
    }

}
