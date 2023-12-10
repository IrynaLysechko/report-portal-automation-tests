package com.epam.report.portal.ui.bo;

import com.epam.report.portal.ui.pages.DashboardPageObject;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DashboardBusinessObject {

    private final DashboardPageObject dashboardPage = new DashboardPageObject();

    @Step
    public void createTestWidgetOnDashboard(String widgetName) {
        dashboardPage
                .openDashboardPage()
                .clickAddNewWidgetButton()
                .selectAvailableWidgetType()
                .clickNextStep()
                .selectDemoFilter()
                .clickNextStep()
                .setWidgetNameToInput(widgetName)
                .clickAddButton();
        log.info("Widget with name '{}' was created on dashboard", widgetName);
    }

    @Step
    public void goToAddNewWidgetModalWindow() {
        dashboardPage
                .openDashboardPage()
                .clickAddNewWidgetButton();
    }

    @Step
    public void goToEditWidgetModalWindow(String widgetName) {
        dashboardPage
                .openDashboardPage()
                .hoverOverWidgetHeader(widgetName)
                .clickEditWidgetButton(widgetName)
                .verifyEditWidgetWindowIsOpen();
    }

    @Step
    public void deleteWidgetFromDashboard(String widgetName) {
        dashboardPage
                .openDashboardPage()
                .hoverOverWidgetHeader(widgetName)
                .clickDeleteWidgetButton(widgetName)
                .verifyDeleteWidgetWindowIsOpen()
                .clickConfirmToDeleteWidgetButton();
        log.info("Widget with name '{}' was deleted from dashboard", widgetName);
    }
}
