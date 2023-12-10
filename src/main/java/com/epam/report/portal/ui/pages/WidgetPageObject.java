package com.epam.report.portal.ui.pages;

import com.epam.report.portal.factory.driver.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Slf4j
public class WidgetPageObject extends AbstractPage {

    private String widgetNameInputXpath = "//input[@placeholder='Enter widget name']";
    private String widgetDescriptionInputXpath =  "//textarea[@placeholder='Enter widget description']";
    private String saveButtonXpath = "//button[text()='Save']";
    private String widgetCountInputXpath = "(//input[contains(@class,'type-text variant-standard')])[1]";
    private String widgetViewButtonXpath = "//span[text()='%s']";

    private String filterEditButtonXpath = "//span[contains(@class, 'pencil-icon')]";
    private String filterItemXpath = "//span[text()='%s']/ancestor::div[contains(@class, 'filtersItem')]";
    private String filterRadioButtonXpath = "//span[text()='%s']/ancestor::span[contains(@class, 'inputRadio')]";
    private String editFilterButtonXpath = "//span[text()='%s']/ancestor::span[contains(@class, 'inputRadio')]//span[contains(@class, 'pencil')]";
    private String filterEntitiesSelectorXpath = "//div[contains(@class, 'entitiesSelector__toggler')]";
    private String entityValueInputXpath = "//input[@placeholder='Enter quantity']";
    private String entityValue = "//span[text()='%s']";

    private String submitButtonXpath = "//button[text()='Submit']";
    private String closeModalWindowIconXpath = "//div[contains(@class, 'close-modal-icon')]";

    public WidgetPageObject setWidgetNameToInput(String widgetName) {
        WebElement webElement = findByXpath(widgetNameInputXpath);
        webElement.clear();
        webElement.sendKeys(widgetName);
        return this;
    }

    public WidgetPageObject setWidgetDescriptionToInput(String widgetDescription) {
        WebElement webElement = findByXpath(widgetDescriptionInputXpath);
        webElement.clear();
        webElement.sendKeys(widgetDescription);
        return this;
    }

    public WidgetPageObject setWidgetItemsCountToInput(String widgetItemsCount) {
        WebElement webElement = findByXpath(widgetCountInputXpath);
        webElement.clear();
        webElement.sendKeys(widgetItemsCount);
        return this;
    }

    public WidgetPageObject clickSaveButton() {
        findByXpath(saveButtonXpath).click();
        return this;
    }

    public WidgetPageObject changeWidgetView(String view) {
        findByXpath(format(widgetViewButtonXpath, view)).click();
        return this;
    }

    public WidgetPageObject clickEditWidgetFilterButton() {
        findByXpath(editFilterButtonXpath).click();
        return this;
    }

    public WidgetPageObject selectWidgetFilter(String filterName) {
        WebElement webElement = findByXpath(format(filterRadioButtonXpath, filterName));
        if (!webElement.isSelected()) {
            webElement.click();
            log.info("selected filter with name {}", filterName);
        }
        return this;
    }

    public WidgetPageObject hoverOverWidgetFilter(String filterName) {
        new Actions(DriverManager.getDriver())
                .moveToElement(findByXpath(format(filterItemXpath, filterName)))
                .build()
                .perform();
        return this;
    }

    public WidgetPageObject clickEditFilterButton() {
        findByXpath(filterEditButtonXpath)
                .click();
        return this;
    }

    public WidgetPageObject clickFilterSelector() {
        findByXpath(filterEntitiesSelectorXpath)
                .click();
        return this;
    }

    public WidgetPageObject selectAdditionalAttribute(String attribute) {
       findByXpath(format(entityValue, attribute))
               .click();
        return this;
    }

    public WidgetPageObject setAttributeValueToInput(String attributeValue) {
        WebElement webElement = findByXpath(entityValueInputXpath);
        webElement.clear();
        webElement.sendKeys(attributeValue);
        return this;
    }

    public WidgetPageObject clickSubmitButton() {
        findByXpath(submitButtonXpath).click();
        return this;
    }

    public WidgetPageObject clickCloseModalWindowsButton() {
        findByXpath(closeModalWindowIconXpath).click();
        return this;
    }
}
