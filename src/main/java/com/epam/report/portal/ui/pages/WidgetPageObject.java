package com.epam.report.portal.ui.pages;

import com.epam.report.portal.factory.driver.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.epam.report.portal.utils.Waits.waitUntilElementAppears;

@Slf4j
public class WidgetPageObject extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class, 'edit-widget-modal')]")
    private WebElement editWidgetWindow;

    @FindBy(xpath = "//input[@placeholder='Enter widget name']")
    private WebElement editWidgetNameInput;

    @FindBy(xpath = "//textarea[@placeholder='Enter widget description']")
    private WebElement editWidgetDescriptionInput;

    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "(//input[contains(@class,'type-text variant-standard')])[1]")
    private WebElement editWidgetCountInput;

    @FindBy(xpath = "//span[text()='Bar view']")
    private WebElement barViewButton;

    @FindBy(xpath = "//span[contains(@class, 'pencil-icon')]")
    private WebElement editWidgetFilterButton;

    @FindBy(xpath = "//span[text()='my filter']/ancestor::div[contains(@class, 'filtersItem')]")
    private WebElement filterItem;

    @FindBy(xpath = "//span[text()='my filter']/ancestor::span[contains(@class, 'inputRadio')]")
    private WebElement filterRadioButton;

    @FindBy(xpath = "//span[text()='my filter']/ancestor::span[contains(@class, 'inputRadio')]//span[contains(@class, 'pencil')]")
    private WebElement filterEditButton;

    @FindBy(xpath = "//div[contains(@class, 'entitiesSelector__toggler')]")
    private WebElement filterSelector;

    @FindBy(xpath = "//button[text()='Submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@placeholder='Enter quantity']")
    private WebElement attributeValueInput;

    @FindBy(xpath = "//div[contains(@class, 'close-modal-icon')]")
    private WebElement closeModalWindowButton;

    public WidgetPageObject verifyEditWidgetWindowIsOpen() {
        waitUntilElementAppears(editWidgetWindow);
        return this;
    }

    public WidgetPageObject setWidgetNameToInput(String widgetName) {
        editWidgetNameInput.clear();
        editWidgetNameInput.sendKeys(widgetName);
        return this;
    }

    public WidgetPageObject setWidgetDescriptionToInput(String widgetDescription) {
        editWidgetDescriptionInput.clear();
        editWidgetDescriptionInput.sendKeys(widgetDescription);
        return this;
    }

    public WidgetPageObject setWidgetItemsCountToInput(String widgetItemsCount) {
        editWidgetCountInput.clear();
        editWidgetCountInput.sendKeys(widgetItemsCount);
        return this;
    }

    public WidgetPageObject clickSaveButton() {
        saveButton.click();
        return this;
    }

    public WidgetPageObject changeWidgetView() {
        barViewButton.click();
        return this;
    }

    public WidgetPageObject clickEditWidgetFilterButton() {
        editWidgetFilterButton.click();
        return this;
    }

    public WidgetPageObject selectTestWidgetFilter() {
        if (!filterRadioButton.isSelected()) {
            filterRadioButton.click();
            log.info("selected test widget filter");
        }
        return this;
    }

    public WidgetPageObject hoverOverTestFilter() {
        new Actions(DriverManager.getDriver())
                .moveToElement(filterItem)
                .build()
                .perform();
        return this;
    }

    public WidgetPageObject clickEditTestFilterButton() {
        filterEditButton.click();
        return this;
    }

    public WidgetPageObject clickFilterSelector() {
        filterSelector.click();
        return this;
    }

    public WidgetPageObject selectAdditionalAttribute(String attribute) {
        String xpath = String.format("//span[text()='%s']", "Product bug");
        WebElement webElement = DriverManager.getDriver().findElement(By.xpath(xpath));
        webElement.click();
        return this;
    }

    public WidgetPageObject setAttributeValueToInput(String attributeValue) {
        attributeValueInput.clear();
        attributeValueInput.sendKeys(attributeValue);
        return this;
    }

    public WidgetPageObject clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public WidgetPageObject clickCloseModalWindowsButton() {
        closeModalWindowButton.click();
        log.info("Closed modal window");
        return this;
    }
}
