package com.epam.report.portal.utils;

import com.epam.report.portal.factory.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    public static void waitUntilUrlContains(String url) {
        new WebDriverWait(DriverManager.getDriver(), 20)
                .until(ExpectedConditions.urlContains(url));
    }

    public static void waitUntilElementAppears(WebElement webElement) {
        new WebDriverWait(DriverManager.getDriver(), 20)
                .until(ExpectedConditions.visibilityOf(webElement));
    }
}
