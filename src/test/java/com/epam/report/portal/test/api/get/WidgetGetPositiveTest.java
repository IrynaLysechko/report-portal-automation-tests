package com.epam.report.portal.test.api.get;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.entity.FilterResponse;
import com.epam.report.portal.entity.WidgetPreviewData;
import com.epam.report.portal.test.BaseTest;
import com.epam.report.portal.test.api.data.DataProviderAPI;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

@Test(groups = {"api", "get", "all"})
@Epic("Get API Request")
@Feature("Positive tests")
public class WidgetGetPositiveTest extends BaseTest {

    @Test
    public void verifyUserIsAbleToGetAllProjectWidgets() {
        new WidgetApiClient()
                .getAllWidgetsName()
                .verifyStatusCode(HttpStatus.SC_OK);
    }

    @Test
    public void verifyUserIsAbleToFilterWidgetsByName() {
        String filterValue = "LAUNCH STATISTICS AREA";
        FilterResponse filterResponse = new WidgetApiClient()
                .sendRequestToFilterWidgets("filter.eq.name", filterValue)
                .verifyStatusCode(HttpStatus.SC_OK)
                .getResponse()
                .as(FilterResponse.class);
        Assertions.assertThat(filterResponse.getContent()).contains(filterValue);
    }

    @Test(dataProviderClass = DataProviderAPI.class,
            dataProvider = "widgetIdProvider")
    public void verifyUserIsAbleToGetWidgetById(int widgetId) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(HttpStatus.SC_OK);
    }

    @Test(dataProviderClass = DataProviderAPI.class,
            dataProvider = "widgetIdAndNameProvider")
    public void verifyUserIsAbleToReceiveWidgetByIdWithCorrectName(int widgetId, String widgetName) {
        new WidgetApiClient()
                .getWidgetById(widgetId)
                .verifyStatusCode(HttpStatus.SC_OK)
                .verifyResponseField("name", widgetName);
    }

    @Test(dataProviderClass = DataProviderAPI.class,
            dataProvider = "widgetPreviewProvider")
    public void verifyUserIsAbleToGetAWidgetPreview(WidgetPreviewData widgetPreviewData) {
        new WidgetApiClient()
                .sendWidgetPreviewRequest(widgetPreviewData)
                .verifyStatusCode(HttpStatus.SC_OK);
    }

}
