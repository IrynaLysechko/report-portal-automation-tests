package com.epam.report.portal.test.api.delete;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.test.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.epam.report.portal.utils.FileReader.readFileFromTestResourcesToString;

public class WidgetDeletePositiveTest extends BaseTest {

   @Test
   public void verifyUserIsAbleToDeleteWidgetFromDashboardByID() throws IOException {
       String widgetJson = readFileFromTestResourcesToString(
               "test/widgetsJson/project_activity_panel_widget.json");
       int widgetId = new WidgetApiClient()
               .sendRequestToCreateWidgetOnDashboard(widgetJson)
               .verifyStatusCode(HttpStatus.SC_CREATED)
               .getResponse()
               .jsonPath()
               .getInt("id");
       new WidgetApiClient()
               .sendRequestToDeleteWidgetFromDashboard(widgetId)
               .verifyStatusCode(HttpStatus.SC_OK);
   }
}