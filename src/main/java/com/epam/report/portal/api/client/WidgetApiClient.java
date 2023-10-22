package com.epam.report.portal.api.client;

import com.epam.report.portal.config.AppConfiguration;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class WidgetApiClient extends BaseApiClient<WidgetApiClient>{

    private static final String BASE_URI = AppConfiguration.getReportPortalUrl();
    private static final String BASE_PATH = "api/v1/" + AppConfiguration.getReportPortalProjectName() + "/widget";

    private final RequestSpecification widgetRequestSpecification = given()
            .header("Authorization", "bearer " + AppConfiguration.getBearerToken());

    @Step
    public WidgetApiClient getWidgetById(int widgetId) {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .pathParam("widgetId", widgetId)
                .get(BASE_URI + "api/v1/{projectName}/widget/{widgetId}")
        );

        return this;
    }

    @Step
    public WidgetApiClient getAllWidgetsName() {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .get(BASE_URI + "api/v1/{projectName}/widget/names/all"));
        return this;
    }
}
