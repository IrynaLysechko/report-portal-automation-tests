package com.epam.report.portal.test.api.post;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.test.api.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.epam.report.portal.test.Constants.WIDGET_JSON_PATH;
import static com.epam.report.portal.utils.FileReader.readFileFromTestResourcesToString;

@Test(groups = {"api", "post", "all"})
@Epic("Post API Request")
@Feature("Positive tests")
public class WidgetPostPositiveTest extends BaseTest {

    @Test
    public void verifyUserIsAbleToCreateWidgetWithValidData() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                WIDGET_JSON_PATH + "project_activity_panel_widget.json");
        int widgetId = new WidgetApiClient()
                .sendRequestToCreateWidgetOnDashboard(widgetJson)
                .verifyStatusCode(HttpStatus.SC_CREATED)
                .getResponse()
                .jsonPath()
                .getInt("id");
        new WidgetApiClient()
                .sendRequestToDeleteWidgetFromDashboard(widgetId);
    }
}
