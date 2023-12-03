package com.epam.report.portal.test.api.post;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.test.api.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.epam.report.portal.test.Constants.WIDGET_JSON_PATH;
import static com.epam.report.portal.test.api.data.MessageConstants.*;
import static com.epam.report.portal.utils.FileReader.readFileFromTestResourcesToString;

@Test(groups = {"api", "post", "all"})
@Epic("Post API Request")
@Feature("Negative tests")
public class WidgetPostNegativeTest extends BaseTest {

    @Test
    public void verifyUserIsNotAbleToCreateWidgetWithMissedRequiredField() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                WIDGET_JSON_PATH + "widget_with_missed_widget_type_field.json");
        new WidgetApiClient()
                .sendRequestToCreateWidgetOnDashboard(widgetJson)
                .verifyStatusCode(HttpStatus.SC_BAD_REQUEST)
                .verifyResponseFieldContainsValue("message",
                        String.format(FIELD_IS_NULL_MESSAGE, "widgetType"));
    }

    @Test
    public void verifyUserIsNotAbleToCreateWidgetWithInvalidWidgetType() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                WIDGET_JSON_PATH + "widget_with_invalid_widget_type.json");
        new WidgetApiClient()
                .sendRequestToCreateWidgetOnDashboard(widgetJson)
                .verifyStatusCode(HttpStatus.SC_BAD_REQUEST)
                .verifyResponseFieldContainsValue("message",
                        String.format(VALUE_NOT_ALLOWED_MESSAGE, "widgetType"));
    }
}
