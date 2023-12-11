package com.epam.report.portal.test.ui;

import com.epam.report.portal.ui.bo.DashboardBusinessObject;
import com.epam.report.portal.ui.bo.WidgetBusinessObject;
import com.epam.report.portal.ui.pages.DashboardPageObject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Objects;

import static com.epam.report.portal.test.ui.data.DataProviderUI.*;

@Test(groups = {"ui", "edit", "all"})
@Epic("Widget UI test")
@Feature("Verify user is able to edit widget")
public class WidgetEditTest extends BaseTest {

    private String updatedWidgetName;
    private String testWidgetName;

    @BeforeMethod
    public void createTestWidget() {
        testWidgetName = "TEST_WIDGET_" + RandomStringUtils.randomAlphanumeric(4);
        new DashboardBusinessObject()
                .createTestWidgetOnDashboard(testWidgetName);
    }

    @Test(description = "Verify user is able to add new entities to widget filter")
    public void verifyUserIsAbleToEditWidgetFilter() {
        String additionalParameterName = "Product bug";
        String additionalParameterValue = "2;";
        new DashboardBusinessObject()
                .goToEditWidgetModalWindow(testWidgetName);
        String displayedFilterInfo = new WidgetBusinessObject()
                .addParameterToWidgetFilter(TEST_FILTER, additionalParameterName, additionalParameterValue)
                .getInfoAboutFilter(TEST_FILTER);
        new WidgetBusinessObject()
                .closeWidgetModalWindow();
        Assertions.assertThat(displayedFilterInfo).contains(additionalParameterName, additionalParameterValue);
    }

    @Test(description = "Verify user is able to change widget name")
    public void verifyUserIsAbleToChangeWidgetName() {
        updatedWidgetName = "Test widget update " + RandomStringUtils.randomAlphanumeric(4);
        DashboardBusinessObject dashboardBusinessObject = new DashboardBusinessObject();
        dashboardBusinessObject
                .goToEditWidgetModalWindow(testWidgetName);
        new WidgetBusinessObject()
                .updateWidgetName(updatedWidgetName);
        List<String> existingWidgetNames = dashboardBusinessObject
                .getExistingWidgetsNamesOnDashboard();
        Assertions.assertThat(existingWidgetNames).contains(updatedWidgetName);
    }

    @Test(description = "Verify user is able to change widget description")
    public void verifyUserIsAbleToChangeWidgetDescription() {
        new DashboardBusinessObject()
                .goToEditWidgetModalWindow(testWidgetName);
        new WidgetBusinessObject()
                .updateWidgetDescription(TEST_WIDGET_DESCRIPTION);
        String description = new DashboardBusinessObject()
                .getWidgetDescriptionOnDashboard(testWidgetName);
        Assertions.assertThat(description).isEqualTo(TEST_WIDGET_DESCRIPTION);
    }

    @Test(description = "Verify user is able to change widget items count")
    public void verifyUserIsAbleToChangeWidgetItemsCount() {
        String itemCountToSet = "20";
        new DashboardBusinessObject()
                .goToEditWidgetModalWindow(testWidgetName);
        new WidgetBusinessObject()
                .updateWidgetItemCount(itemCountToSet);
        new DashboardBusinessObject()
                .goToEditWidgetModalWindow(testWidgetName);
        String widgetItemCount = new WidgetBusinessObject()
                .getWidgetItemCount();
        new WidgetBusinessObject()
                .closeWidgetModalWindow();
        Assertions.assertThat(widgetItemCount).isEqualTo(itemCountToSet);
    }

    @Test(description = "Verify user is able to change widget view type")
    public void verifyUserIsAbleToChangeWidgetViewType() {
        new DashboardBusinessObject()
                .goToEditWidgetModalWindow(testWidgetName);
        new WidgetBusinessObject()
                .updateWidgetView(WIDGET_BAR_VIEW);
        String widgetView = new DashboardBusinessObject()
                .getWidgetViewOnDashboard(testWidgetName);
        Assertions.assertThat(widgetView).isEqualTo(WIDGET_BAR_VIEW);
    }

    @Test(description = "Verify user is able to increase widget size on the dashboard")
    public void verifyUserIsAbleToIncreaseWidgetOnDashboard() {
        SoftAssert softAssert = new SoftAssert();
        DashboardPageObject dashboardPage = new DashboardPageObject()
                .openDashboardPage();
        int initialWidth = dashboardPage
                .getWidgetWidth(testWidgetName);
        int initialHeight = dashboardPage
                .getWidgetHeight(testWidgetName);
        dashboardPage
                .performWidgetResize(testWidgetName, 100, 50);
        int increasedWidth = dashboardPage
                .getWidgetWidth(testWidgetName);
        int increasedHeight = dashboardPage
                .getWidgetHeight(testWidgetName);
        softAssert.assertTrue(increasedHeight > initialHeight,
                "Height should be increased");
        softAssert.assertTrue(increasedWidth > initialWidth,
                "Width should be increased");
        softAssert.assertAll();
    }

    @Test(description = "Verify user is able to decrease widget size on the dashboard")
    public void verifyUserIsAbleToDecreaseWidgetOnDashboard() {
        SoftAssert softAssert = new SoftAssert();
        DashboardPageObject dashboardPage = new DashboardPageObject()
                .openDashboardPage();
        int initialWidth = dashboardPage
                .getWidgetWidth(testWidgetName);
        int initialHeight = dashboardPage
                .getWidgetHeight(testWidgetName);
        dashboardPage
                .performWidgetResize(testWidgetName,-200, -150);
        int decreasedWidth = dashboardPage
                .getWidgetWidth(testWidgetName);
        int decreasedHeight = dashboardPage
                .getWidgetHeight(testWidgetName);
        softAssert.assertTrue(decreasedHeight < initialHeight,
                "Height should be decreased");
        softAssert.assertTrue(decreasedWidth < initialWidth,
                "Width should be decreased");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void deleteTestWidget() {
        new DashboardBusinessObject()
                .deleteWidgetFromDashboard(Objects.requireNonNullElse(updatedWidgetName, testWidgetName));
    }
}
