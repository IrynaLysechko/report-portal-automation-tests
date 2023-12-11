package com.epam.report.portal.ui.bo;

import com.epam.report.portal.ui.pages.DashboardPageObject;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.List;

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
                .waitForEditWidgetWindowOpening();
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

    @Step
    public List<String> getExistingWidgetsNamesOnDashboard() {
        List<String> widgetNames = dashboardPage
                .getWidgetsName();
        log.info("Existing widgets on dashboard {}", widgetNames);
        return widgetNames;
    }

    @Step
    public String getWidgetDescriptionOnDashboard(String widgetName) {
        return dashboardPage
                .getWidgetDescription(widgetName);
    }

    @Step
    public String getWidgetViewOnDashboard(String widgetName) {
        return dashboardPage
                .getWidgetViewName(widgetName);
    }

    @Step
    public String getWidgetContainerPosition(String widgetName) {
        String styleAttribute = dashboardPage.getWidgetStyleAttribute(widgetName);
        log.info("style {}", styleAttribute);

        String position = "";
        if (StringUtils.isNotBlank(styleAttribute) && styleAttribute.contains("translate")) {
            position = StringUtils.strip(StringUtils
                    .substringBetween(styleAttribute, "translate(", ")"));
        }

        log.info("position is {}", position);
        return StringUtils.isNotBlank(position) ? position : null;
    }

    @Step
    public void moveWidgetOnDashboard(String from, String to) {
        dashboardPage
                .performDragAndDropAction(from, to);
    }

    @Step
    public void goToPassedDemoLaunch(String widgetName) {
        dashboardPage
                .openDashboardPage()
                .hoverOverPassedDemoLaunch(widgetName);
    }

    @Step
    public boolean scrollToWidgetOnDashboard(String widgetName) {
        return dashboardPage
                .openDashboardPage()
                .scrollToWidget(widgetName)
                .isElementScrolledIntoView(widgetName);
    }
}
