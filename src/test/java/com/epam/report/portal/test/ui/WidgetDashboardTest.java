package com.epam.report.portal.test.ui;

import com.epam.report.portal.entity.WidgetTypesContainer;
import com.epam.report.portal.ui.pages.AddNewWidgetPageObject;
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
import static com.epam.report.portal.test.ui.data.DataProviderUI.*;

@Test(groups = {"ui", "dashboard", "all"})
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
        dashboardBusinessObject
                .goToAddNewWidgetModalWindow();
        List<String> availableWidgetTypes = new AddNewWidgetPageObject()
                .getAvailableWidgetTypesList();
        Assertions.assertThat(availableWidgetTypes)
                .containsExactlyInAnyOrderElementsOf(expectedWidgetTypes);
    }

    @Test(description = "User is able to move widgets on the dashboard")
    public void verifyUserIsAbleToMoveWidgetsOnTheDashboard() {
        String barContainerPositionBefore = dashboardBusinessObject
                .getWidgetContainerPosition(LAUNCH_AREA_WIDGET);
        dashboardBusinessObject
                .moveWidgetOnDashboard(LAUNCH_AREA_WIDGET, LAUNCH_BAR_WIDGET);
        String areaContainerPositionAfter = dashboardBusinessObject
                .getWidgetContainerPosition(LAUNCH_AREA_WIDGET);
        Assertions.assertThat(areaContainerPositionAfter)
                .isNotEqualTo(barContainerPositionBefore);
    }

    @Test(description = "Verify user is able to navigate to appropriate launch view after clicking on widget content")
    public void verifyUserIsAbleToNavigateToWidgetLaunchView() {
        dashboardBusinessObject
                .goToPassedDemoLaunch(LAUNCH_BAR_WIDGET);
        String launchPageUrl = new LaunchPageObject()
                .waitUntilLaunchPageIsOpened()
                .getLaunchPageUrl();
        Assertions.assertThat(launchPageUrl).contains("PASSED");
    }

    @Test(description = "Verify user is able to scroll to widget on dashboard")
    public void verifyUserIsAbleToScrollToWidgetOnDashboard() {
        boolean isScrolled = dashboardBusinessObject
                .scrollToWidgetOnDashboard(FAILED_CASES_TREND);
        Assertions.assertThat(isScrolled).isTrue();
    }
}
