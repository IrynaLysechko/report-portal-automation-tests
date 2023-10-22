package com.epam.report.portal.testRunner.testng.test;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.testRunner.testng.BaseTest;
import com.epam.report.portal.testRunner.testng.data.WidgetDataProvider;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class WidgetAPITest extends BaseTest {

    @Test(dataProviderClass = WidgetDataProvider.class,
            dataProvider = "widgetIdProvider")
    public void verifyUserIsAbleToGetWidgetById(int widgetId) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(HttpStatus.SC_OK);
    }

    @Test
    public void verifyUserCanGetAllWidgetsName() {
        new WidgetApiClient()
                .getAllWidgetsName()
                .verifyStatusCode(HttpStatus.SC_OK);
    }
}
