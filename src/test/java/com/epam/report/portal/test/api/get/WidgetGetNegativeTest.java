package com.epam.report.portal.test.api.get;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.test.api.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(groups = {"api", "get", "all"})
@Epic("Get API Request")
@Feature("Negative tests")
public class WidgetGetNegativeTest extends BaseTest {

    @Test
    public void verifyUserReceivesUnauthorizedResponseWithoutTokenProvided() {
        new WidgetApiClient()
                .sendUnauthorizedRequestToGetWidgets()
                .verifyStatusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void verifyUserReceiveDeniedResponseForNotExistingProjectName() {
        new WidgetApiClient()
                .sendRequestForNotExistingProjectName()
                .verifyStatusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void verifyUserReceiveNotFoundResponseForNotExistingWidgetId() {
        new WidgetApiClient()
                .getWidgetById(124857)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void verifyUserReceiveBadRequestResponseForMalformedWidgetId() {
        new WidgetApiClient()
                .getWidgetById("adc")
                .verifyStatusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
