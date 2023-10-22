package com.epam.report.portal.api.client;

import com.epam.report.portal.config.AppConfiguration;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class WidgetApiClient extends BaseApiClient<WidgetApiClient>{

    private static final String BASE_URI = AppConfiguration.getReportPortalUrl();
    private static final String BASE_PATH = "api/v1/" + AppConfiguration.getReportPortalProjectName() + "/widget";

    private final RequestSpecification widgetRequestSpecification = given()
            .baseUri(BASE_URI)
            .basePath(BASE_PATH);

    public WidgetApiClient getWidgetById(int widgetId) {
        setResponse(given()
                .header("Authorization", "bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTgwMTUxNjAsInVzZXJfbmFtZSI6ImlyeW5hX2x5c2VjaGtvIiwianRpIjoiTEV4X3JNSGc4R0dqM0tNYXZEb1VKUHhtbEZRIiwiY2xpZW50X2lkIjoidWkiLCJzY29wZSI6WyJ1aSJdfQ.vfeBQWJ6ZzbnMzmqRpbmGaiI4IXfqQueQhcMKgLjvb8")
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .pathParam("widgetId", widgetId)
                .get(BASE_URI + "api/v1/{projectName}/widget/{widgetId}")
        );

        return this;
    }

    public WidgetApiClient getAllWidgetsName() {
        setResponse(given()
                .header("Authorization", "bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTc3NTc3NTcsInVzZXJfbmFtZSI6ImlyeW5hX2x5c2VjaGtvIiwianRpIjoiMFlJQUU4X1Z5TnpBX3Y1bkJDN1JSMklFdWNrIiwiY2xpZW50X2lkIjoidWkiLCJzY29wZSI6WyJ1aSJdfQ.37kf5BH-RvdaqA38qHzMPs1mGLjmz8c1RpgT4-pI9ic")
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .get(BASE_URI + "api/v1/{projectName}/widget/names/all"));
        return this;
    }
}
