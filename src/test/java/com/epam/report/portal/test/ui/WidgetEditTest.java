package com.epam.report.portal.test.ui;

import com.epam.report.portal.ui.bo.DashboardBusinessObject;
import com.epam.report.portal.ui.bo.WidgetBusinessObject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static com.epam.report.portal.test.ui.data.DataProviderUI.TEST_FILTER;
import static com.epam.report.portal.test.ui.data.DataProviderUI.TEST_WIDGET;

@Epic("Widget UI test")
@Feature("Verify user is able to edit widget")
public class WidgetEditTest extends BaseTest {

    private String updatedWidgetName;

    @BeforeMethod(dependsOnMethods = "logIn")
    public void createTestWidget() {
        new DashboardBusinessObject()
                .createTestWidgetOnDashboard(TEST_WIDGET);
    }

    @Test
    public void verifyUserIsAbleToEditWidgetFilter() {
        new DashboardBusinessObject()
                .goToEditWidgetModalWindow(TEST_WIDGET);
        new WidgetBusinessObject()
                .addParameterToWidgetFilter(TEST_FILTER, "Product bug", "2")
                .closeWidgetModalWindow();
    }

    @Test
    public void verifyUserIsAbleToChangeWidgetName() {
        updatedWidgetName = "Test widget update";
        new DashboardBusinessObject()
                .goToEditWidgetModalWindow(TEST_WIDGET);
        new WidgetBusinessObject()
                .updateWidgetName(updatedWidgetName);
    }

    @Test
    public void verifyUserIsAbleToChangeWidgetDescription() {
        new DashboardBusinessObject()
                .goToEditWidgetModalWindow(TEST_WIDGET);
        new WidgetBusinessObject()
                .updateWidgetDescription( "Widget created for testing purposes");
    }

    @Test
    public void verifyUserIsAbleToChangeWidgetItemsCount() {
        new DashboardBusinessObject()

                .goToEditWidgetModalWindow(TEST_WIDGET);
        new WidgetBusinessObject()
                .updateWidgetItemCount("20");
    }

    @Test
    public void verifyUserIsAbleToChangeWidgetViewType() {
        new DashboardBusinessObject()
                .goToEditWidgetModalWindow(TEST_WIDGET);
        new WidgetBusinessObject()
                .updateWidgetView("Bar view");
    }

    @AfterMethod(alwaysRun = true)
    public void deleteTestWidget() {
        new DashboardBusinessObject()
                .deleteWidgetFromDashboard(Objects.requireNonNullElse(updatedWidgetName, TEST_WIDGET));
    }
}
