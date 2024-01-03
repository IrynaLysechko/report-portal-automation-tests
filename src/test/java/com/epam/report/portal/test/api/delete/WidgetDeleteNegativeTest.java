package com.epam.report.portal.test.api.delete;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.test.api.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(groups = {"delete", "all", "api"})
@Epic("Delete API Request")
@Feature("Negative tests")
public class WidgetDeleteNegativeTest extends BaseTest {

    @Test
    public void verifyUserReceiveNotFoundResponseWhenTryToDeleteWidgetByNotExistingID() {
        new WidgetApiClient()
                .sendRequestToDeleteWidgetFromDashboard(12)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND);
    }
}
