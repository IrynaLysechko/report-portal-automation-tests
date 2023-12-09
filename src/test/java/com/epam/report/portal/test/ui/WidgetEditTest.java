package com.epam.report.portal.test.ui;

import com.epam.report.portal.ui.bo.DashboardBusinessObject;
import com.epam.report.portal.ui.bo.WidgetBusinessObject;
import com.epam.report.portal.ui.pages.DashboardPageObject;
import com.epam.report.portal.ui.pages.WidgetPageObject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

@Epic("Widget UI test")
@Feature("Verify user is able to edit widget")
public class WidgetEditTest extends BaseTest {

    private String updatedWidgetName;
    private final String TEST_WIDGET = "Test Widget";
    private final DashboardBusinessObject dashboardBusinessObject = new DashboardBusinessObject();

    @BeforeMethod(dependsOnMethods = "logIn")
    public void createTestWidget() {
        dashboardBusinessObject
                .createTestWidgetOnDashboard(TEST_WIDGET);
    }

    @Test
    public void verifyUserIsAbleToEditWidgetFilter() {
        dashboardBusinessObject
                .goToEditWidgetModalWindow();
        new WidgetBusinessObject()
                .addParameterToTestWidgetFilter("Product bug", "2")
                .closeWidgetModalWindow();
    }

    @Test
    public void verifyUserIsAbleToChangeWidgetName() {
        updatedWidgetName = "Test widget update";
        dashboardBusinessObject
                .goToEditWidgetModalWindow();
        new WidgetBusinessObject()
                .updateWidgetName(updatedWidgetName);
    }

    @Test
    public void verifyUserIsAbleToChangeWidgetDescription() {
        dashboardBusinessObject
                .goToEditWidgetModalWindow();
        new WidgetBusinessObject()
                .updateWidgetDescription( "Widget created for testing purposes");
    }

    @Test
    public void verifyUserIsAbleToChangeWidgetItemsCount() {
        dashboardBusinessObject
                .goToEditWidgetModalWindow();
        new WidgetBusinessObject()
                .updateWidgetItemCount("20");
    }

    @Test
    public void verifyUserIsAbleToChangeWidgetViewType() {
        dashboardBusinessObject
                .goToEditWidgetModalWindow();
        new WidgetBusinessObject()
                .updateWidgetView();
    }

    @AfterMethod(alwaysRun = true)
    public void deleteTestWidget() {
        dashboardBusinessObject
                .deleteWidgetFromDashboard(Objects.requireNonNullElse(updatedWidgetName, TEST_WIDGET));
    }
}
