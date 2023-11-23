package com.epam.report.portal.test.api.post;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.test.BaseTest;
import org.apache.http.HttpStatus;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.epam.report.portal.utils.FileReader.readFileFromTestResourcesToString;

public class WidgetPostNegativeTest extends BaseTest {

    @Test
    public void verifyUserIsNotAbleToCreateWidgetWithMissedRequiredField() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                "test/widgetsJson/widget_with_missed_widget_type_field.json");
        new WidgetApiClient()
                .sendRequestToCreateWidgetOnDashboard(widgetJson)
                .verifyStatusCode(HttpStatus.SC_BAD_REQUEST)
                .verifyResponseFieldContainsValue("message",
                        "Field 'widgetType' should not be null");
    }

    @Test
    public void verifyUserIsNotAbleToCreateWidgetWithInvalidWidgetType() throws IOException {
        String widgetJson = readFileFromTestResourcesToString(
                "test/widgetsJson/widget_with_invalid_widget_type.json");
        new WidgetApiClient()
                .sendRequestToCreateWidgetOnDashboard(widgetJson)
                .verifyStatusCode(HttpStatus.SC_BAD_REQUEST)
                .verifyResponseFieldContainsValue("message",
                        "Value is not allowed for field 'widgetType'");
    }

}
