package com.epam.report.portal.ui.pages;

import io.qameta.allure.Step;
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

    private String widgetFiltersEditButtonXpath = "//span[contains(@class, 'pencil-icon')]";
    private String filterItemXpath = "//span[text()='%s']/ancestor::div[contains(@class, 'filtersItem')]";
    private String widgetFilterNameXpath = "//span[contains(@class, 'filterName__bold')]";
    private String filterRadioButtonXpath = "//span[text()='%s']//ancestor::span[contains(@class, 'inputRadio')]";
    private String filterInfoXpath = "//span[text()='%s']/ancestor::span[contains(@class, 'inputRadio')]//p";
    private String editFilterButtonXpath = "//span[contains(@class, 'pencil')]";
    private String filterEntitiesSelectorXpath = "//div[contains(@class, 'entitiesSelector__toggler')]";
    private String entityValueInputXpath = "//input[@placeholder='Enter quantity']";
    private String entityValue = "//span[text()='%s']";


    private String submitButtonXpath = "//button[text()='Submit']";
    private String closeModalWindowIconXpath = "//div[contains(@class, 'close-modal-icon')]";

    @Step
    public WidgetPageObject setWidgetNameToInput(String widgetName) {
        clearAndSendKeys(findByXpath(widgetNameInputXpath), widgetName);
        return this;
    }

    @Step
    public WidgetPageObject setWidgetDescriptionToInput(String widgetDescription) {
        clearAndSendKeys(findByXpath(widgetDescriptionInputXpath), widgetDescription);
        return this;
    }

    @Step
    public WidgetPageObject setWidgetItemsCountToInput(String widgetItemsCount) {
        clearAndSendKeys(findByXpath(widgetCountInputXpath), widgetItemsCount);
        return this;
    }

    @Step
    public String getWidgetItemCount() {
        return getAttribute(widgetCountInputXpath, "value");
    }

    @Step
    public WidgetPageObject clickSaveButton() {
        click(saveButtonXpath);
        return this;
    }

    @Step
    public WidgetPageObject changeWidgetView(String view) {
        click(format(widgetViewButtonXpath, view));
        return this;
    }

    @Step
    public WidgetPageObject clickEditWidgetFilterButton() {
        click(editFilterButtonXpath);
        return this;
    }

    @Step
    public WidgetPageObject selectWidgetFilter(String filterName) {
        WebElement webElement = findByXpath(format(filterRadioButtonXpath, filterName));
        if (!webElement.isSelected()) {
            webElement.click();
            log.info("selected filter with name {}", filterName);
        }
        return this;
    }

    @Step
    public String getWidgetFilterName() {
        return findByXpath(widgetFilterNameXpath).getText();
    }

    @Step
    public String getFilterInfo(String filterName) {
        return getText(format(filterInfoXpath, filterName));
    }

    @Step
    public WidgetPageObject hoverOverWidgetFilter(String filterName) {
        new Actions(driver)
                .moveToElement(findByXpath(format(filterItemXpath, filterName)))
                .build()
                .perform();
        return this;
    }

    @Step
    public WidgetPageObject clickEditFilterButton() {
        click(widgetFiltersEditButtonXpath);
        return this;
    }

    @Step
    public WidgetPageObject clickFilterSelector() {
        click(filterEntitiesSelectorXpath);
        return this;
    }

    @Step
    public WidgetPageObject selectAdditionalAttribute(String attribute) {
       click(format(entityValue, attribute));
        return this;
    }

    @Step
    public WidgetPageObject setAttributeValueToInput(String attributeValue) {
        clearAndSendKeys(findByXpath(entityValueInputXpath), attributeValue);
        return this;
    }

    @Step
    public WidgetPageObject clickSubmitButton() {
        click(submitButtonXpath);
        return this;
    }

    @Step
    public WidgetPageObject clickCloseModalWindowsButton() {
        click(closeModalWindowIconXpath);
        return this;
    }
}
