package com.epam.report.portal.ui.pages;

import com.epam.report.portal.utils.Waits;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.epam.report.portal.config.AppConfiguration.getProjectDashboardId;
import static com.epam.report.portal.config.AppConfiguration.getProjectDashboardUIURI;
import static com.epam.report.portal.utils.Waits.waitUntilElementAppears;

@Slf4j
public class DashboardPageObject extends AbstractPage {

    private final String addNewWidgetButtonXpath = "//button[@type='button']/span[text()='Add new widget']";

    private final String widgetContainerXpath = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]";
    private final String widgetDraggableElementXpath = "//div[text()='%s']/ancestor::div[contains(@class, 'draggable-field')]";
    private final String widgetResizableElementXpath = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//span[contains(@class, 'resizable')]";
    private final String widgetHeaderElementXpath = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'widgetHeader__info-block')]";
    private final String widgetEditButtonXpath = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'widgetHeader__controls-block')]/div[1]";
    private final String deleteWidgetButtonXpath = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'widgetHeader__controls-block')]/div[3]";
    private final String fullyPassedDemoLaunchFromWidget = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//div[contains(@class, 'launch-statistics-chart')]//*[local-name()='g' and contains(@class, 'c3-chart-bars')]//*[local-name()='g' and contains(@class, 'c3-shapes-statistics$executions$passed')]//*[local-name()='path'][5]";

    private final String firstAvailableWidgetTypeRadioButtonXpath = "//div[@class='widget-type-selector']//div[2]//span[contains(@class, 'inputRadio__at-top')]";
    private final String nextStepButtonXpath = "//span[text()='Next step']/ancestor::button";
    private final String editWidgetModalWindowXpath = "//div[contains(@class, 'edit-widget-modal')]";
    private final String demoFilterRadioButtonXpath = "//span[contains(@class,'inputRadio__at')]";
    private final String editWidgetNameInputXpath = "//input[@placeholder='Enter widget name']";
    private final String addButtonXpath = "//button[text()='Add']";
    private final String deleteWidgetModalWindowXpath = "//div[contains(@class, 'modal-window-animation-enter-done')]";
    private final String confirmationDeleteWidgetButtonXpath = "//button[text()='Delete']";

    private final String DASHBOARD_URI = getProjectDashboardUIURI() + "/" + getProjectDashboardId();

    public DashboardPageObject openDashboardPage() {
        openPage(DASHBOARD_URI);
        return this;
    }

    @Step
    public void verifyUserIsLogIn() {
        Waits.waitUntilUrlContains(getProjectDashboardUIURI());
    }

    public DashboardPageObject verifyEditWidgetWindowIsOpen() {
        waitUntilElementAppears(findByXpath(editWidgetModalWindowXpath));
        return this;
    }

    public DashboardPageObject clickAddNewWidgetButton() {
        findByXpath(addNewWidgetButtonXpath).click();
        return this;
    }

    public DashboardPageObject performDragAndDropAction(String widgetNameFrom, String widgetNameTo) {
        WebElement webElementFrom = findByXpath(
                format(widgetDraggableElementXpath, widgetNameFrom));
        WebElement webElementTo = findByXpath(
                format(widgetDraggableElementXpath, widgetNameTo));
        new Actions(driver)
                .dragAndDrop(webElementFrom, webElementTo)
                .build()
                .perform();
        return this;
    }

    public String getWidgetContainerPosition(String widgetName) {
        WebElement widget = findByXpath(format(widgetContainerXpath, widgetName));
        return getContainerPosition(widget);
    }

    public void performWidgetResize(String widgetName, int horizontalOffset, int verticalOffset) {
        WebElement webElement = findByXpath(format(widgetResizableElementXpath, widgetName));
        new Actions(driver)
                .clickAndHold(webElement)
                .moveByOffset(horizontalOffset, verticalOffset)
                .release()
                .build()
                .perform();
    }

    public int getWidgetWidth(String widgetName) {
        WebElement webElement = findByXpath(format(widgetContainerXpath, widgetName));
        int width = webElement.getSize().getWidth();
        log.info("widget width {}", width);
        return width;
    }

    public int getWidgetHeight(String widgetName){
        WebElement webElement = findByXpath(format(widgetContainerXpath, widgetName));
        int height = webElement.getSize().getWidth();
        log.info("widget height {}", height);
        return height;
    }

    public DashboardPageObject hoverOverWidgetHeader(String widgetName) {
        WebElement webElement = findByXpath(format(widgetHeaderElementXpath, widgetName));
        new Actions(driver)
                .moveToElement(webElement)
                .build()
                .perform();
        return this;
    }

    public DashboardPageObject clickEditWidgetButton(String widgetName) {
        WebElement webElement = findByXpath(format(widgetEditButtonXpath, widgetName));
        webElement.click();
        return this;
    }

    public DashboardPageObject selectAvailableWidgetType() {
        findByXpath(firstAvailableWidgetTypeRadioButtonXpath)
                .click();
        return this;
    }

    public DashboardPageObject clickNextStep() {
        findByXpath(nextStepButtonXpath)
                .click();
        return this;
    }

    public DashboardPageObject selectDemoFilter() {
        findByXpath(demoFilterRadioButtonXpath)
                .click();
        return this;
    }

    public DashboardPageObject setWidgetNameToInput(String widgetName) {
        WebElement webElement = findByXpath(editWidgetNameInputXpath);
        webElement.clear();
        webElement.sendKeys(widgetName);
        return this;
    }

    public DashboardPageObject clickAddButton() {
        findByXpath(addButtonXpath)
                .click();
        return this;
    }

    public DashboardPageObject clickDeleteWidgetButton(String widgetName) {
        findByXpath(format(deleteWidgetButtonXpath, widgetName))
                .click();
        return this;
    }

    public DashboardPageObject verifyDeleteWidgetWindowIsOpen() {
        waitUntilElementAppears(findByXpath(deleteWidgetModalWindowXpath));
        return this;
    }

    public DashboardPageObject clickConfirmToDeleteWidgetButton() {
        findByXpath(confirmationDeleteWidgetButtonXpath)
                .click();
        return this;
    }

    public DashboardPageObject hoverOverPassedDemoLaunch(String widgetName) {
        WebElement webElement = findByXpath(format(fullyPassedDemoLaunchFromWidget, widgetName));
        new Actions(driver)
                .moveToElement(webElement)
                .click()
                .build()
                .perform();
        return this;
    }

    private String getContainerPosition(WebElement webElement) {
        String styleAttribute = webElement.getAttribute("style");
        log.info("style {}", styleAttribute);
        String position = "";
        if (StringUtils.isNotBlank(styleAttribute) && styleAttribute.contains("translate")) {
            position = StringUtils.strip(StringUtils
                    .substringBetween(styleAttribute, "translate(", ")"));
        }

        log.info("position is {}", position);
        return StringUtils.isNotBlank(position) ? position : null;
    }
}