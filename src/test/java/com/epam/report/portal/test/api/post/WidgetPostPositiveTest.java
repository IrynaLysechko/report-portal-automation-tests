package com.epam.report.portal.test.api.post;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.test.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.epam.report.portal.utils.FileReader.readFileFromTestResourcesToString;

public class WidgetPostPositiveTest extends BaseTest {

    @Test
    public void verifyUserIsAbleToCreateWidgetWithValidData() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                "test/widgetsJson/project_activity_panel_widget.json");
        new WidgetApiClient()
                .sendRequestToCreateWidgetOnDashboard(widgetJson)
                .verifyStatusCode(HttpStatus.SC_CREATED);
    }
}
