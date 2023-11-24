package com.epam.report.portal.test.api.put;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.test.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.epam.report.portal.utils.FileReader.readFileFromTestResourcesToString;

public class WidgetPutNegativeTest extends BaseTest {

    @Test
    public void verifyUserReceivesNotFoundResponseWhenTryToUpdateNonExistingWidget() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                "test/widgetsJson/project_activity_panel_widget.json");
        new WidgetApiClient()
                .sendRequestToUpdateWidget(1, widgetJson)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND)
                .verifyResponseFieldContainsValue("message", String.format("Widget with ID '%s' not found on " +
                        "project '%s'. Did you use correct Widget ID?", AppConfiguration.getAppName(), 1));
    }

    @Test
    public void verifyUserReceiveFoundResponseWhenTryToUpdateNonExistingWidget() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                "test/widgetsJson/project_activity_panel_widget.json");
        int widgetId = new WidgetApiClient()
                .sendRequestToCreateWidgetOnDashboard(widgetJson)
                .verifyStatusCode(HttpStatus.SC_CREATED)
                .getResponse()
                .jsonPath()
                .getInt("id");
        String invalidWidgetJson = readFileFromTestResourcesToString(
                "test/widgetsJson/widget_with_missed_widget_type_field.json");
        new WidgetApiClient()
                .sendRequestToUpdateWidget(widgetId, invalidWidgetJson)
                .verifyStatusCode(HttpStatus.SC_BAD_REQUEST)
                .verifyResponseFieldContainsValue("message", "Incorrect Request");
    }
}
