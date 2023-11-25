package com.epam.report.portal.api.client;

import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.entity.WidgetPreviewData;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
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
    public WidgetApiClient getWidgetById(String widgetId) {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .pathParam("widgetId", widgetId)
                .get("{projectName}/widget/{widgetId}")
        );
        return this;
    }

    @Step
    public WidgetApiClient sendWidgetPreviewRequest(WidgetPreviewData widgetPreviewData) {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .contentType(ContentType.JSON)
                .body(widgetPreviewData.toJson())
                .post("{projectName}/widget/preview"));
        return this;
    }

    @Step
    public WidgetApiClient getAllWidgetsName() {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .get("{projectName}/widget/names/all"));
        return this;
    }

    @Step
    public WidgetApiClient sendRequestToFilterWidgets(String filterName, String filterValue) {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .queryParam(filterName, filterValue)
                .get("{projectName}/widget/names/all"));
        return this;
    }

    @Step
    public WidgetApiClient sendUnauthorizedRequestToGetWidgets() {
        setResponse(given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .get("{projectName}/widget/names/all"));
        return this;
    }

    @Step
    public WidgetApiClient sendRequestForNotExistingProjectName() {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", "personal")
                .get("{projectName}/widget/names/all"));
        return this;
    }

    @Step
    public WidgetApiClient sendRequestToCreateWidgetOnDashboard(String widgetJson) {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .contentType(ContentType.JSON)
                .body(widgetJson)
                .post("{projectName}/widget")
        );
        return this;
    }

    @Step
    public WidgetApiClient sendRequestToDeleteWidgetFromDashboard(int widgetId) {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .pathParam("dashboardId", AppConfiguration.getProjectDashboardId())
                .pathParam("widgetId", widgetId)
                .delete("{projectName}/dashboard/{dashboardId}/{widgetId}"));
        return this;
    }

    @Step
    public WidgetApiClient sendRequestToUpdateWidget(int widgetId, String updatedWidget) {
        setResponse(widgetRequestSpecification
                .pathParam("projectName", AppConfiguration.getReportPortalProjectName())
                .pathParam("widgetId", widgetId)
                .contentType(ContentType.JSON)
                .body(updatedWidget)
                .put("{projectName}/widget/{widgetId}"));
        return this;
    }
}
