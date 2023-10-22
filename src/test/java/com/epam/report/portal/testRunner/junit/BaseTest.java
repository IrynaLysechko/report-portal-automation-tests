package com.epam.report.portal.testRunner.junit;

import com.epam.report.portal.api.client.AuthenticationApiClient;
import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.listeners.junit.TestListener;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestListener.class)
public class BaseTest {

    @BeforeAll
    public static void setAccessToken() {
        String token = new AuthenticationApiClient()
                .sendAccessTokenRequest()
                .verifyStatusCode(HttpStatus.SC_OK)
                .getAccessTokenValue();
        AppConfiguration.setBearerToken(token);
    }
}
