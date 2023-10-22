package com.epam.report.portal.testRunner.junit;

import com.epam.report.portal.api.client.AuthenticationApiClient;
import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.listeners.junit.TestListener;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@ExtendWith(TestListener.class)
public class WidgetAPITest {

    @BeforeAll
    public static void setAccessToken() {
        String token = new AuthenticationApiClient().getAccessTokenValue();
        AppConfiguration.setBearerToken(token);
    }

    @ParameterizedTest
    @MethodSource("com.epam.report.portal.testRunner.junit.WidgetDataProvider#widgetIdProvider")
    public void verifyUserIsAbleToGetWidgetById(int widgetId) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(HttpStatus.SC_OK);
    }
}
