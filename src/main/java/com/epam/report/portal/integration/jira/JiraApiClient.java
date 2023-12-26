package com.epam.report.portal.integration.jira;

import com.epam.report.portal.api.client.BaseApiClient;
import com.epam.report.portal.config.AppConfiguration;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

@Slf4j
public class JiraApiClient extends BaseApiClient<JiraApiClient> {

    private final String BASE_URI = AppConfiguration.getJiraURI();
    private final String BASE_PATH = "/rest/api/2/";

    private final RequestSpecification jiraRequestSpec = given()
            .auth()
            .oauth2(AppConfiguration.getJiraToken())
            .baseUri(BASE_URI)
            .basePath(BASE_PATH);

    @Step
   public void updateJiraTicketStatus(String issueId, String status) {
       String id  = getStatusTransitionsId(issueId, status);
       String body = String.format("{\"transition\": {\"id\": \"%s\"}}", id);
       setResponse(jiraRequestSpec
               .contentType(ContentType.JSON)
               .body(body)
               .post("issue/" + issueId + "/transitions"));
       verifyStatusCode(HttpStatus.SC_NO_CONTENT);
   }

   private String getStatusTransitionsId(String issueId, String status) {
       setResponse(jiraRequestSpec
               .get("issue/" + issueId + "/transitions"));
       String id = verifyStatusCode(HttpStatus.SC_OK)
               .getResponse()
               .jsonPath()
               .getString(String.format("transitions.find { it.name == '%s' }.id", status));
       log.info("issue {} transition id for status {} is [{}]", issueId, status, id);
       return id;
   }
}
