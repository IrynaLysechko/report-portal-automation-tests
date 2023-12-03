package com.epam.report.portal.factory.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

@Slf4j
public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        log.info("browser is " + browser.toUpperCase());
        DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());
        WebDriverManager.getInstance(driverManagerType).setup();
        WebDriver webDriver;

        switch (driverManagerType) {
            case CHROME:
                webDriver = new ChromeDriver();
                break;
            case SAFARI:
                webDriver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException(driverManagerType.toString());
        }

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        webDriver.manage().window().maximize();

        return webDriver;
    }
}
