package com.epam.report.portal.ui.pages;

import com.epam.report.portal.factory.driver.DriverManager;
import com.epam.report.portal.utils.Waits;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.epam.report.portal.config.AppConfiguration.getProjectDashboardId;
import static com.epam.report.portal.config.AppConfiguration.getProjectDashboardUIURI;
import static com.epam.report.portal.utils.Waits.waitUntilElementAppears;

@Slf4j
public class DashboardPageObject extends AbstractPage {

    @FindBy(xpath = "//button[@type='button']/span[text()='Add new widget']")
    private WebElement addNewWidgetButton;

    @FindBy(xpath = "//div[text()='LAUNCH STATISTICS AREA']/ancestor::div[contains(@style, 'transform')]")
    private WebElement launchStatisticsAreaContainer;

    @FindBy(xpath = "//div[text()='LAUNCH STATISTICS BAR']/ancestor::div[contains(@style, 'transform')]")
    private WebElement launchStatisticsBarContainer;

    @FindBy(xpath = "//div[text()='LAUNCH STATISTICS AREA']/ancestor::div[contains(@class, 'draggable-field')]")
    private WebElement launchStatisticsAreaDragableField;

    @FindBy(xpath = "//div[text()='LAUNCH STATISTICS BAR']/ancestor::div[contains(@class, 'draggable-field')]")
    private WebElement launchStatisticsBarDragableField;

    @FindBy(xpath = "//div[text()='LAUNCH STATISTICS AREA']/ancestor::div[contains(@style, 'transform')]//span[contains(@class, 'resizable')]")
    private WebElement launchStatisticsAreaResizableElement;

    @FindBy(xpath = "//div[text()='Test Widget']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'widgetHeader__info-block')]")
    private WebElement testWidgetHeader;

    @FindBy(xpath = "//div[text()='LAUNCH STATISTICS BAR']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'widgetHeader__controls-block')]/div[1]")
    private WebElement launchStatisticsBarEditButton;

    @FindBy(xpath = "//div[@class='widget-type-selector']//div[2]//span[contains(@class, 'inputRadio__at-top')]")
    private WebElement firstAvailableWidgetTypeRadioButton;

    @FindBy(xpath = "//span[text()='Next step']/ancestor::button")
    private WebElement nextStepButton;

    @FindBy(xpath = "//div[text()='Test Widget']/ancestor::div[contains(@style, 'transform')]")
    private WebElement testWidgetContainer;

    @FindBy(xpath = "//div[text()='Test Widget']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'widgetHeader__controls-block')]/div[1]")
    private WebElement testWidgetEditButton;

    @FindBy(xpath = "//div[contains(@class, 'edit-widget-modal')]")
    private WebElement editWidgetWindow;

    @FindBy(xpath = "//span[contains(@class,'inputRadio__at')]")
    private WebElement demoFilterRadioButton;

    @FindBy(xpath = "//input[@placeholder='Enter widget name']")
    private WebElement editWidgetNameInput;

    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//div[text()='Test Widget']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'widgetHeader__controls-block')]/div[3]")
    private WebElement testWidgetDeleteButton;

    @FindBy(xpath = "//div[contains(@class, 'modal-window-animation-enter-done')]")
    private WebElement deleteWidgetModalWindow;

    @FindBy(xpath = "//button[text()='Delete']")
    private WebElement confirmToDeleteWidgetButton;

    @FindBy(xpath = "//div[text()='LAUNCH STATISTICS BAR']/ancestor::div[contains(@style, 'transform')]//div[contains(@class, 'launch-statistics-chart')]//*[local-name()='g' and contains(@class, 'c3-chart-bars')]//*[local-name()='g' and contains(@class, 'c3-shapes-statistics$executions$passed')]//*[local-name()='path'][5]")
    private WebElement passedDemoLaunch;

    private String DASHBOARD_URI = getProjectDashboardUIURI() + "/" + getProjectDashboardId();

    public DashboardPageObject openDashboardPage() {
        openPage(DASHBOARD_URI);
        return this;
    }

    @Step
    public void verifyUserIsLogIn() {
        Waits.waitUntilUrlContains(getProjectDashboardUIURI());
    }

    public DashboardPageObject verifyEditWidgetWindowIsOpen() {
        waitUntilElementAppears(editWidgetWindow);
        return this;
    }

    public DashboardPageObject clickAddNewWidgetButton() {
        addNewWidgetButton.click();
        return this;
    }

    public DashboardPageObject dragAndDropLaunchStatisticsContainers() {
        new Actions(DriverManager.getDriver())
                .dragAndDrop(launchStatisticsAreaDragableField, launchStatisticsBarDragableField)
                .build()
                .perform();
        return this;
    }

    public String getLaunchStatisticsAreaContainerPosition() {
        log.debug("area position");
        return getContainerPosition(launchStatisticsAreaContainer);
    }

    public String getLaunchStatisticsBarContainerPosition() {
        log.debug("bar position");
        return getContainerPosition(launchStatisticsBarContainer);
    }

    public void performWidgetRezise(int horizontalOffset, int verticalOffset) {
        new Actions(DriverManager.getDriver())
                .clickAndHold(launchStatisticsAreaResizableElement)
                .moveByOffset(horizontalOffset, verticalOffset)
                .release()
                .build()
                .perform();
    }

    public int getWidgetWidth(){
        int width = launchStatisticsAreaContainer.getSize().getWidth();
        log.info("widget width {}", width);
        return width;
    }

    public int getWidgetHeight(){
        int height = launchStatisticsAreaContainer.getSize().getWidth();
        log.info("widget height {}", height);
        return height;
    }

    public DashboardPageObject hoverOverWidgetHeader() {
        new Actions(DriverManager.getDriver())
                .moveToElement(testWidgetHeader)
                .build()
                .perform();
        return this;
    }

    public DashboardPageObject clickEditWidgetButton() {
        testWidgetEditButton.click();
        return this;
    }

    public DashboardPageObject selectAvailableWidgetType() {
        firstAvailableWidgetTypeRadioButton.click();
        return this;
    }

    public DashboardPageObject clickNextStep() {
        nextStepButton.click();
        return this;
    }

    public DashboardPageObject selectDemoFilter() {
        demoFilterRadioButton.click();
        return this;
    }

    public DashboardPageObject setWidgetNameToInput(String widgetName) {
        editWidgetNameInput.clear();
        editWidgetNameInput.sendKeys(widgetName);
        return this;
    }

    public DashboardPageObject clickAddButton() {
        addButton.click();
        return this;
    }

    public DashboardPageObject clickDeleteTestWidgetButton() {
        testWidgetDeleteButton.click();
        return this;
    }

    public DashboardPageObject verifyDeleteWidgetWindowIsOpen() {
        waitUntilElementAppears(deleteWidgetModalWindow);
        return this;
    }

    public DashboardPageObject clickConfirmToDeleteWidgetButton() {
        confirmToDeleteWidgetButton.click();
        return this;
    }

    public DashboardPageObject clickDeleteButtonForSpecifiedWidget(String widgetName) {
        String xpath = String.format(
                "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'widgetHeader__controls-block')]/div[3]",
                widgetName);
        WebElement deleteButton = DriverManager.getDriver().findElement(By.xpath(xpath));
        deleteButton.click();
        return this;
    }

    public DashboardPageObject hoverOverSpecifiedWidgetHeader(String widgetName) {
        String xpath = String.format(
                "//div[text()='%s']/ancestor::div[contains(@style, 'transform')]//div[contains(@class,'widgetHeader__info-block')]",
                widgetName);
        WebElement widgetHeader = DriverManager.getDriver().findElement(By.xpath(xpath));
        new Actions(DriverManager.getDriver())
                .moveToElement(widgetHeader)
                .build()
                .perform();
        return this;
    }

    public DashboardPageObject hoverOverPassedDemoLaunch() {
        new Actions(DriverManager.getDriver())
                .moveToElement(passedDemoLaunch)
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