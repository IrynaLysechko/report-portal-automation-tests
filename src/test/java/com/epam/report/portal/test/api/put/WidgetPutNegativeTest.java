package com.epam.report.portal.test.api.put;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.test.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.epam.report.portal.test.Constants.WIDGET_JSON_PATH;
import static com.epam.report.portal.test.api.data.MessageConstants.*;
import static com.epam.report.portal.utils.FileReader.readFileFromTestResourcesToString;

@Test(groups = {"api", "put", "all"})
@Epic("Put API Request")
@Feature("Negative tests")
public class WidgetPutNegativeTest extends BaseTest {

    @Test
    public void verifyUserReceivesNotFoundResponseWhenTryToUpdateNonExistingWidget() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                WIDGET_JSON_PATH + "project_activity_panel_widget.json");
        new WidgetApiClient()
                .sendRequestToUpdateWidget(1, widgetJson)
                .verifyStatusCode(HttpStatus.SC_NOT_FOUND)
                .verifyResponseFieldContainsValue("message", String.format(
                        WIDGET_NOT_FOUND_MESSAGE, 1, AppConfiguration.getReportPortalProjectName()));
    }

    @Test
    public void verifyUserReceiveBadRequestResponseWhenTryToUpdateWidgetWithInvalidData() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                WIDGET_JSON_PATH + "project_activity_panel_widget.json");
        int widgetId = new WidgetApiClient()
                .sendRequestToCreateWidgetOnDashboard(widgetJson)
                .verifyStatusCode(HttpStatus.SC_CREATED)
                .getResponse()
                .jsonPath()
                .getInt("id");
        String invalidWidgetJson = readFileFromTestResourcesToString(
                WIDGET_JSON_PATH + "widget_with_missed_widget_type_field.json");
        new WidgetApiClient()
                .sendRequestToUpdateWidget(widgetId, invalidWidgetJson)
                .verifyStatusCode(HttpStatus.SC_BAD_REQUEST)
                .verifyResponseFieldContainsValue("message", INCORRECT_REQUEST_MESSAGE);
        new WidgetApiClient()
                .sendRequestToDeleteWidgetFromDashboard(widgetId);
    }
}
