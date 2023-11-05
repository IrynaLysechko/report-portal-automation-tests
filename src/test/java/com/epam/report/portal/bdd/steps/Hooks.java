package com.epam.report.portal.bdd.steps;

import com.epam.report.portal.api.client.AuthenticationApiClient;
import com.epam.report.portal.config.AppConfiguration;
import io.cucumber.java.BeforeAll;
import org.apache.http.HttpStatus;

public class Hooks {

    @BeforeAll(order = 1)
    public static void setApplicationConfigs() {
        AppConfiguration.setReportPortalProperties();
    }

    @BeforeAll(order = 2)
    public static void setAccessToken() {
        String token = new AuthenticationApiClient()
                .sendAccessTokenRequest()
                .verifyStatusCode(HttpStatus.SC_OK)
                .getAccessTokenValue();
        AppConfiguration.setBearerToken(token);
    }
}
