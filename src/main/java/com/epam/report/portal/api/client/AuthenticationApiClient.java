package com.epam.report.portal.api.client;

import com.epam.report.portal.config.AppConfiguration;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.epam.report.portal.config.AppConfiguration.getUserName;
import static com.epam.report.portal.config.AppConfiguration.getUserPassword;
import static io.restassured.RestAssured.given;

@Slf4j
public class AuthenticationApiClient extends BaseApiClient<AuthenticationApiClient> {

    private final String BASE_URL = AppConfiguration.getReportPortalUrl();
    private final String AUTHENTICATION_URL = BASE_URL + "uat/sso/oauth/token";

    @Step
    public String getAccessTokenValue() {
        sendAccessTokenRequest();
        String accessToken = getResponse().jsonPath().get("access_token");
        log.info("access token value is {}", accessToken);
        return accessToken;
    }

    @Step
    private void sendAccessTokenRequest() {
        setResponse(given()
                .headers("Authorization", "Basic dWk6dWltYW4=",
                        "content-type", "application/x-www-form-urlencoded")
                .formParams("grant_type", "password",
                        "username", getUserName(),
                        "password", getUserPassword())
                .post(AUTHENTICATION_URL));
    }
}
