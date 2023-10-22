package com.epam.report.portal.testRunner.testng;

import com.epam.report.portal.api.client.AuthenticationApiClient;
import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.listeners.testng.TestListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class WidgetAPITest {

    @BeforeClass
    public void setAccessToken() {
        String token = new AuthenticationApiClient().getAccessTokenValue();
        AppConfiguration.setBearerToken(token);
    }

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
