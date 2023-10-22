package com.epam.report.portal.testRunner.testng;

import com.epam.report.portal.api.client.AuthenticationApiClient;
import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.listeners.testng.TestListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeSuite
    public void setAccessToken() {
        String token = new AuthenticationApiClient()
                .sendAccessTokenRequest()
                .verifyStatusCode(HttpStatus.SC_OK)
                .getAccessTokenValue();
        AppConfiguration.setBearerToken(token);
    }
}
