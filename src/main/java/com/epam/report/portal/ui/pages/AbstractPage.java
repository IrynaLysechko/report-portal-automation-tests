package com.epam.report.portal.ui.pages;

import com.epam.report.portal.factory.driver.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Slf4j
public class AbstractPage {

    public WebDriver driver;

    public AbstractPage() {
        driver = DriverManager.getDriver();
    }

    public AbstractPage openPage(String url) {
        driver.get(url);
        return this;
    }

    public WebElement findByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public List<WebElement> findListByXpath(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    public WebElement findByName(String name) {
        return driver.findElement(By.name(name));
    }

    public String format(String format, Object ... arguments) {
        return String.format(format, arguments);
    }

    public void clearAndSendKeys(WebElement webElement, String ... keys) {
        webElement.clear();
        webElement.sendKeys(keys);
    }

    public String getText(String xpath) {
        return findByXpath(xpath).getText();
    }

    public String getAttribute(String xpath, String attribute) {
        return findByXpath(xpath).getAttribute(attribute);
    }

    public void click(String xpath) {
        findByXpath(xpath).click();
    }
}
