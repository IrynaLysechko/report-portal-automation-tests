package com.epam.report.portal.ui.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.report.portal.config.AppConfiguration.getProjectDashboardId;
import static com.epam.report.portal.config.AppConfiguration.getProjectDashboardUIURI;
import static com.epam.report.portal.utils.Waits.waitUntilElementAppears;

@Slf4j
public class DashboardPageObject extends AbstractPage {

    private final String addNewWidgetButtonXpath = "//button[@type='button']/span[text()='Add new widget']";

    private final String widgetsNameXpath = "//div[contains(@class,'widget-name-block')]";
    private final String widgetContainerXpath = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]";
    private final String widgetDraggableElementXpath = "//div[text()='%s']/ancestor::div[contains(@class, 'draggable-field')]";
    private final String widgetResizableElementXpath = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//span[contains(@class, 'resizable')]";
    private final String widgetHeaderElementXpath = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'widgetHeader__info-block')]";
    private final String widgetDescriptionTooltipIconXpath = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//div[contains(@class, 'description')]";
    private final String widgetEditButtonXpath = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'widgetHeader__controls-block')]/div[1]";
    private final String widgetViewXpath = "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'info-block')]";
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

    @Step
    public DashboardPageObject openDashboardPage() {
        openPage(DASHBOARD_URI);
        return this;
    }

    @Step
    public List<String> getWidgetsName() {
        return findListByXpath(widgetsNameXpath)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step
    public DashboardPageObject waitForEditWidgetWindowOpening() {
        waitUntilElementAppears(findByXpath(editWidgetModalWindowXpath));
        return this;
    }

    @Step
    public String getWidgetDescription(String widgetName) {
        return getAttribute(format(widgetDescriptionTooltipIconXpath, widgetName), "content");
    }

    @Step
    public String getWidgetViewName(String widgetName) {
        return getText(format(widgetViewXpath, widgetName));
    }

    @Step
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

    @Step
    public String getWidgetStyleAttribute(String widgetName) {
        return getAttribute(format(widgetContainerXpath, widgetName), "style");
    }

    @Step
    public void performWidgetResize(String widgetName, int horizontalOffset, int verticalOffset) {
        scrollToWidget(widgetName);
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

    @Step
    public int getWidgetHeight(String widgetName) {
        WebElement webElement = findByXpath(format(widgetContainerXpath, widgetName));
        int height = webElement.getSize().getWidth();
        log.info("widget height {}", height);
        return height;
    }

    @Step
    public DashboardPageObject hoverOverWidgetHeader(String widgetName) {
        WebElement webElement = findByXpath(format(widgetHeaderElementXpath, widgetName));
        new Actions(driver)
                .moveToElement(webElement)
                .build()
                .perform();
        return this;
    }

    @Step
    public DashboardPageObject clickEditWidgetButton(String widgetName) {
        click(format(widgetEditButtonXpath, widgetName));
        return this;
    }

    @Step
    public DashboardPageObject selectAvailableWidgetType() {
        click(firstAvailableWidgetTypeRadioButtonXpath);
        return this;
    }

    @Step
    public DashboardPageObject clickNextStep() {
        click(nextStepButtonXpath);
        return this;
    }

    @Step
    public DashboardPageObject selectDemoFilter() {
        click(demoFilterRadioButtonXpath);
        return this;
    }

    @Step
    public DashboardPageObject setWidgetNameToInput(String widgetName) {
        clearAndSendKeys(findByXpath(editWidgetNameInputXpath), widgetName);
        return this;
    }

    @Step
    public DashboardPageObject clickAddButton() {
        click(addButtonXpath);
        return this;
    }

    @Step
    public DashboardPageObject clickDeleteWidgetButton(String widgetName) {
        click(format(deleteWidgetButtonXpath, widgetName));
        return this;
    }

    @Step
    public DashboardPageObject verifyDeleteWidgetWindowIsOpen() {
        waitUntilElementAppears(findByXpath(deleteWidgetModalWindowXpath));
        return this;
    }

    @Step
    public DashboardPageObject clickConfirmToDeleteWidgetButton() {
        click(confirmationDeleteWidgetButtonXpath);
        return this;
    }

    @Step
    public DashboardPageObject hoverOverPassedDemoLaunch(String widgetName) {
        WebElement webElement = findByXpath(format(fullyPassedDemoLaunchFromWidget, widgetName));
        new Actions(driver)
                .moveToElement(webElement)
                .click()
                .build()
                .perform();
        return this;
    }

    @Step
    public DashboardPageObject scrollToWidget(String widgetName) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
                findByXpath(format(widgetHeaderElementXpath, widgetName)));
        return this;
    }

    @Step
    public boolean isElementScrolledIntoView(String widgetName) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (boolean) jsExecutor.executeScript(
                "var elemRect = arguments[0].getBoundingClientRect(),"
                        + "    windowHeight = (window.innerHeight || document.documentElement.clientHeight);"
                        + "return (elemRect.top >= 0 && elemRect.bottom <= windowHeight);",
                findByXpath(format(widgetHeaderElementXpath, widgetName)));
    }

    @Step
    public DashboardPageObject clickAddNewWidgetButton() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();",
                findByXpath(addNewWidgetButtonXpath));
        return this;
    }
}