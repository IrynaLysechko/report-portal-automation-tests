package com.epam.report.portal.integration.slack;

import com.epam.report.portal.api.client.BaseApiClient;
import com.epam.report.portal.config.AppConfiguration;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class SlackApiClient extends BaseApiClient<SlackApiClient> {

    private final String BASE_URI = AppConfiguration.getSlackURI();

    @Step
    public void sendNotificationToSlackChannel(String notification) {
        String body = String.format("{\"text\":\"%s\"}", notification);
        setResponse(given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(BASE_URI));
    }
}
