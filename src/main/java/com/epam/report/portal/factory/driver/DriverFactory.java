package com.epam.report.portal.factory.driver;

import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.utils.SystemPropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        log.info("browser is " + browser.toUpperCase());

        WebDriver webDriver;

        if (browser.equals("remote")) {
            webDriver = createRemoteWebDriver();
        } else {
            DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());
            webDriver = createLocalWebDriver(driverManagerType);
        }

        configureWebDriver(webDriver);

        return webDriver;
    }

    private static WebDriver createRemoteWebDriver() {
        ChromeOptions browserOptions = setRemoteBrowserCapabilities();
        URL url = getUrlSafely(AppConfiguration.getSauceLabRemoteDriverURI());
        return new RemoteWebDriver(url, browserOptions);
    }

    private static WebDriver createLocalWebDriver(DriverManagerType driverManagerType) {
        WebDriverManager.getInstance(driverManagerType).setup();

        switch (driverManagerType) {
            case CHROME:
                return new ChromeDriver();
            case SAFARI:
                return new SafariDriver();
            default:
                throw new IllegalArgumentException(driverManagerType.toString());
        }
    }

    private static void configureWebDriver(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    private static URL getUrlSafely(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL: " + url, e);
        }
    }

    private static ChromeOptions setRemoteBrowserCapabilities() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setCapability("platformName", "Windows 11");
        browserOptions.setCapability("browserVersion", "latest");

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", SystemPropertyUtils.getOrFail("username"));
        sauceOptions.put("accessKey", SystemPropertyUtils.getOrFail("key"));
        sauceOptions.put("build", "selenium-build-5UJN9");
        sauceOptions.put("name", AppConfiguration.getReportPortalProjectName());

        browserOptions.setCapability("sauce:options", sauceOptions);
        return browserOptions;
    }
}
