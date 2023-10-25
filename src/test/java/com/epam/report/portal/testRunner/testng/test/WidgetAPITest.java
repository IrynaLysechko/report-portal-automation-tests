package com.epam.report.portal.testRunner.testng.test;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.entity.WidgetPreviewData;
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

    @Test(dataProviderClass = WidgetDataProvider.class,
            dataProvider = "widgetIdAndNameProvider")
    public void verifyUserIsAbleToReceiveWidgetByIdWithCorrectName(int widgetId, String widgetName) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(HttpStatus.SC_OK)
                .verifyResponseField("name", widgetName);
    }

    @Test(dataProviderClass = WidgetDataProvider.class,
            dataProvider = "csvDataProvider")
    public void verifyStatusCodeIsFailedForWrongWidgetId(String widgetId, int expectedStatusCode) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(expectedStatusCode);
    }

    @Test(dataProviderClass = WidgetDataProvider.class,
            dataProvider = "widgetPreviewProvider")
    public void verifyUserIsAbleToGetAWidgetPreview(WidgetPreviewData widgetPreviewData) {
        new WidgetApiClient()
                .sendWidgetPreviewRequest(widgetPreviewData)
                .verifyStatusCode(HttpStatus.SC_OK);
    }
}
