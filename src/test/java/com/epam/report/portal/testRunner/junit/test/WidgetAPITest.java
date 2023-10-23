package com.epam.report.portal.testRunner.junit.test;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.entity.WidgetPreviewData;
import com.epam.report.portal.testRunner.junit.BaseTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class WidgetAPITest extends BaseTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6})
    public void verifyUserIsAbleToGetWidgetById(int widgetId) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(HttpStatus.SC_OK);
    }

    @ParameterizedTest
    @CsvSource({
            "2, LAUNCH STATISTICS AREA",
            "3, LAUNCH STATISTICS BAR",
            "4, INVESTIGATED PERCENTAGE OF LAUNCHES",
            "5, TEST CASES GROWTH TREND CHART",
            "6, OVERALL STATISTICS PANEL"
    })
    public void verifyUserIsAbleToReceiveWidgetByIdWithCorrectName(int widgetId, String widgetName) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(HttpStatus.SC_OK)
                .verifyResponseField("name", widgetName);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/failed_status_code.csv")
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
