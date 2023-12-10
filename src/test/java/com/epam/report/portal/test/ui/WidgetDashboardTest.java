package com.epam.report.portal.test.ui;

import com.epam.report.portal.entity.WidgetTypesContainer;
import com.epam.report.portal.ui.bo.DashboardBusinessObject;
import com.epam.report.portal.ui.pages.AddNewWidgetPageObject;
import com.epam.report.portal.ui.pages.DashboardPageObject;
import com.epam.report.portal.ui.pages.LaunchPageObject;
import com.epam.report.portal.utils.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static com.epam.report.portal.test.Constants.WIDGET_JSON_PATH;
import static com.epam.report.portal.test.ui.data.DataProviderUI.LAUNCH_AREA_WIDGET;
import static com.epam.report.portal.test.ui.data.DataProviderUI.LAUNCH_BAR_WIDGET;

@Epic("Widget UI test")
@Feature("Verify user is able to interact with widgets on dashboard")
public class WidgetDashboardTest extends BaseTest {

    @Test(description =
            "Verify user is able to see all available widgets types while adding a new widget")
    public void verifyUserIsAbleToSeeAllWidgetsTypesWhenCreateNewWidget() throws IOException {
        String widgetTypesJson = FileReader.readFileFromTestResourcesToString(
                WIDGET_JSON_PATH + "expected_widgets_types.json");
        WidgetTypesContainer container = new ObjectMapper()
                .readValue(widgetTypesJson, WidgetTypesContainer.class);
        List<String> expectedWidgetTypes = container.getWidgetTypes();
        new DashboardBusinessObject()
                .goToAddNewWidgetModalWindow();
        List<String> availableWidgetTypes = new AddNewWidgetPageObject()
                .getAvailableWidgetTypesList();
        Assertions.assertThat(availableWidgetTypes)
                .containsExactlyInAnyOrderElementsOf(expectedWidgetTypes);
    }

    @Test(description = "User is able to move widgets on the dashboard")
    public void verifyUserIsAbleToMoveWidgetsOnTheDashboard() {
        DashboardPageObject dashboardPage = new DashboardPageObject()
                .openDashboardPage();
        String barContainerPositionBefore = dashboardPage
                .getWidgetContainerPosition(LAUNCH_BAR_WIDGET);
        dashboardPage
                .performDragAndDropAction(LAUNCH_AREA_WIDGET, LAUNCH_BAR_WIDGET);
        String areaContainerPositionAfter = dashboardPage
                .getWidgetContainerPosition(LAUNCH_AREA_WIDGET);
        Assertions.assertThat(areaContainerPositionAfter)
                .isEqualTo(barContainerPositionBefore);
    }

    @Test(description = "Verify user is able to resize widgets on the dashboard")
    public void verifyUserIsAbleToResizeWidgetOnDashboard() {
        DashboardPageObject dashboardPage = new DashboardPageObject()
                .openDashboardPage();

        int initialWidth = dashboardPage
                .getWidgetWidth(LAUNCH_AREA_WIDGET);
        int initialHeight = dashboardPage
                .getWidgetHeight(LAUNCH_AREA_WIDGET);
        dashboardPage
                .performWidgetResize(LAUNCH_AREA_WIDGET, 200, 150);
        int increasedWidth = dashboardPage
                .getWidgetWidth(LAUNCH_AREA_WIDGET);
        int increasedHeight = dashboardPage
                .getWidgetHeight(LAUNCH_AREA_WIDGET);
        Assertions.assertThat(increasedWidth).isGreaterThan(initialWidth);
        Assertions.assertThat(increasedHeight).isGreaterThan(initialHeight);

        dashboardPage
                .performWidgetResize(LAUNCH_AREA_WIDGET,-200, -150);
        int decreasedWidth = dashboardPage
                .getWidgetWidth(LAUNCH_AREA_WIDGET);
        int decreasedHeight = dashboardPage
                .getWidgetHeight(LAUNCH_AREA_WIDGET);
        Assertions.assertThat(decreasedWidth).isEqualTo(initialWidth);
        Assertions.assertThat(decreasedHeight).isEqualTo(initialHeight);
    }

    @Test(description = "Verify user is able to navigate to appropriate launch view after clicking on widget content")
    public void verifyUserIsAbleToNavigateToWidgetLaunchView() {
        new DashboardPageObject()
                .openDashboardPage()
                .hoverOverPassedDemoLaunch(LAUNCH_BAR_WIDGET);
        String launchPageUrl = new LaunchPageObject()
                .waitUntilLaunchPageIsOpened()
                .getLaunchPageUrl();
        Assertions.assertThat(launchPageUrl).contains("PASSED");
    }
}
