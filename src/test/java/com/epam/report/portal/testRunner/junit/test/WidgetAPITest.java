package com.epam.report.portal.testRunner.junit.test;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.testRunner.junit.BaseTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class WidgetAPITest extends BaseTest {

    @ParameterizedTest
    @MethodSource("com.epam.report.portal.testRunner.junit.data.WidgetDataProvider#widgetIdProvider")
    public void verifyUserIsAbleToGetWidgetById(int widgetId) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(HttpStatus.SC_OK);
    }
}
