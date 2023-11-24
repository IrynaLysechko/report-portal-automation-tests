package com.epam.report.portal.test.api.delete;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.test.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

@Test(groups = {"delete", "all", "api"})
public class WidgetDeleteNegativeTest extends BaseTest {

    @Test
    public void verifyUserReceiveNotFoundResponseWhenTryToDeleteWidgetByNotExistingID() {
        new WidgetApiClient()
                .sendRequestToDeleteWidgetFromDashboard(12)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND);
    }
}
