package com.epam.report.portal.test.api.put;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.test.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.epam.report.portal.test.Constants.WIDGET_JSON_PATH;
import static com.epam.report.portal.test.api.data.MessageConstants.SUCCESSFULLY_UPDATE_WIDGET_MESSAGE;
import static com.epam.report.portal.utils.FileReader.readFileFromTestResourcesToString;

@Test(groups = {"api", "put", "all"})
@Epic("Put API Request")
@Feature("Positive tests")
public class WidgetPutPositiveTest extends BaseTest {

    @Test
    public void verifyUserIsAbleToUpdateExistingWidget() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                WIDGET_JSON_PATH + "project_activity_panel_widget.json");
        int widgetId = new WidgetApiClient()
                .sendRequestToCreateWidgetOnDashboard(widgetJson)
                .verifyStatusCode(HttpStatus.SC_CREATED)
                .getResponse()
                .jsonPath()
                .getInt("id");
        String updatedWidget = widgetJson
                .replace("Project activity panel_520", "Project activity 1");
        new WidgetApiClient()
                .sendRequestToUpdateWidget(widgetId, updatedWidget)
                .verifyStatusCode(HttpStatus.SC_OK)
                .verifyResponseFieldContainsValue("message",
                        String.format(SUCCESSFULLY_UPDATE_WIDGET_MESSAGE, widgetId));
        new WidgetApiClient()
                .sendRequestToDeleteWidgetFromDashboard(widgetId);
    }
}
