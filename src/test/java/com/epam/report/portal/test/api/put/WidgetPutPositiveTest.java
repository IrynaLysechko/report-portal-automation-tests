package com.epam.report.portal.test.api.put;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.test.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.epam.report.portal.utils.FileReader.readFileFromTestResourcesToString;

public class WidgetPutPositiveTest extends BaseTest {

    @Test
    public void verifyUserIsAbleToUpdateWidget() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                "test/widgetsJson/project_activity_panel_widget.json");
        int widgetId = new WidgetApiClient()
                .sendRequestToCreateWidgetOnDashboard(widgetJson)
                .verifyStatusCode(HttpStatus.SC_CREATED)
                .getResponse()
                .jsonPath()
                .getInt("id");
        String updatedWidget = widgetJson
                .replace("Project activity panel_520", "Project activity");
        new WidgetApiClient()
                .sendRequestToUpdateWidget(widgetId, updatedWidget)
                .verifyStatusCode(HttpStatus.SC_OK)
                .verifyResponseFieldContainsValue("message",
                        String.format("Widget with ID = '%s' successfully updated", widgetId));

    }
}
