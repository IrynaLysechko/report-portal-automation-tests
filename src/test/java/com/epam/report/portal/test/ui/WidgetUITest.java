package com.epam.report.portal.test.ui;

import com.epam.report.portal.ui.pages.AddNewWidgetPageObject;
import com.epam.report.portal.ui.pages.DashboardPageObject;
import org.testng.annotations.Test;

public class WidgetUITest extends BaseTest{

    @Test(description =
            "Verify user is able to see all available widgets types while adding a new widget")
    public void verifyUserIsAbleToSeeAllWidgetsTypesWhenCreateNewWidget() {
        new DashboardPageObject()
                .openDashboardPage()
                .clickAddNewWidgetButton();
        new AddNewWidgetPageObject()
                .getAvailableWidgetTypesList();
    }
}
