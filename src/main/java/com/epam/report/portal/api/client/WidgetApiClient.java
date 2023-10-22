package com.epam.report.portal.api.client;

import com.epam.report.portal.config.AppConfiguration;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class WidgetApiClient extends BaseApiClient<WidgetApiClient>{

    private static final String BASE_URI = AppConfiguration.getReportPortalUrl();
    private static final String BASE_PATH = "api/v1/";

    private final RequestSpecification widgetRequestSpecification = given()
            .baseUri(BASE_URI)
            .basePath(BASE_PATH)
            .header("Authorization", AppConfiguration.getBearerToken());

    @Step
    public WidgetApiClient getWidgetById(int widgetId) {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .pathParam("widgetId", widgetId)
                .get("{projectName}/widget/{widgetId}")
        );
        return this;
    }

    @Step
    public WidgetApiClient getAllWidgetsName() {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .get("{projectName}/widget/names/all"));
        return this;
    }
}
