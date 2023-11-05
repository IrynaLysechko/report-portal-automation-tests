package com.epam.report.portal.testRunner.junit.test;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.entity.WidgetPreviewData;
import com.epam.report.portal.testRunner.junit.BaseTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class WidgetAPITest extends BaseTest {

    @ParameterizedTest
    @ValueSource(ints = {137217, 137218, 137219, 137220, 137221})
    public void verifyUserIsAbleToGetWidgetById(int widgetId) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(HttpStatus.SC_OK);
    }

    @ParameterizedTest
    @CsvSource({
            "137217, LAUNCH STATISTICS AREA",
            "137218, LAUNCH STATISTICS BAR",
            "137219, INVESTIGATED PERCENTAGE OF LAUNCHES",
            "137220, TEST CASES GROWTH TREND CHART",
            "137221, OVERALL STATISTICS PANEL"
    })
    public void verifyUserIsAbleToReceiveWidgetByIdWithCorrectName(int widgetId, String widgetName) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(HttpStatus.SC_OK)
                .verifyResponseField("name", widgetName);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testRunner/failed_status_code.csv")
    public void verifyStatusCodeIsFailedForWrongWidgetId(String widgetId, int expectedStatusCode) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(expectedStatusCode);
    }

    @ParameterizedTest
    @MethodSource("com.epam.report.portal.testRunner.junit.data.WidgetDataProvider#widgetPreviewDataProvider")
    public void verifyUserIsAbleToGetAWidgetPreview(WidgetPreviewData widgetPreviewData) {
        new WidgetApiClient()
                .sendWidgetPreviewRequest(widgetPreviewData)
                .verifyStatusCode(HttpStatus.SC_OK);
    }
}
