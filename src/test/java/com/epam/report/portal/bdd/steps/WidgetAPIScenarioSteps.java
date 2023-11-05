package com.epam.report.portal.bdd.steps;

import com.epam.report.portal.api.client.WidgetApiClient;
import com.epam.report.portal.bdd.context.ScenarioContext;
import com.epam.report.portal.bdd.context.ScenarioVariableKey;
import com.epam.report.portal.entity.WidgetPreviewData;
import com.epam.report.portal.factory.data.WidgetPreviewDataFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class WidgetAPIScenarioSteps {

    private WidgetApiClient widgetApiClient;
    private ScenarioContext scenarioContext;
    private String widgetType;

    @Given("a Widget API client")
    public void createWidgetAPIClient() {
        widgetApiClient = new WidgetApiClient();
    }

    @Given("scenario variable initialization:")
    public void scenarioVariableInitialization(List<Map<String, String>> dataTable) {
        scenarioContext = new ScenarioContext();
        dataTable.forEach(
                dateLine -> scenarioContext.addVariable(
                        dateLine.get(ScenarioVariableKey.SCENARIO_VARIABLE),
                        dateLine.get(ScenarioVariableKey.VALUE)));
    }

    @When("the user retrieves a widget by id {int}")
    public void getWidgetByWidgetId(int widgetId) {
        widgetApiClient
                .getWidgetById(widgetId);
    }

    @Then("the response status code is {int}")
    public void verifyResponseStatusCode(int statusCode) {
        widgetApiClient
                .verifyStatusCode(statusCode);
    }

    @And("^response field ([^\"]*) contains ([^\"]*)")
    public void verifyResponseFieldContainsExpectedValue(String responseField,
                                                         String expectedValue) {
        widgetApiClient
                .verifyResponseField(responseField, expectedValue);
    }

    @When("the user send request to receive all widget names")
    public void getAllWidgetNames() {
        widgetApiClient
                .getAllWidgetsName();
    }

    @And("the response contains following names:")
    public void verifyResponseContainsNames(List<String> widgetNames) {
        SoftAssert softAssert = new SoftAssert();
        String body = widgetApiClient.getResponse().getBody().asString();
        widgetNames.forEach(name ->
                softAssert.assertTrue(body.contains(name), "Response not contains " + name));
        softAssert.assertAll();
    }

    @But("the response not contains following names:")
    public void verifyResponseDoesNotContainNames(List<String> widgetNames) {
        SoftAssert softAssert = new SoftAssert();
        String body = widgetApiClient.getResponse().getBody().asString();
        widgetNames.forEach(name ->
                softAssert.assertFalse(body.contains(name), "Response contains " + name));
        softAssert.assertAll();
    }

    @And("^the user has a widget preview request for widget type ([^\"]*)")
    public void theUserHasAWidgetPreviewRequestForWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    @When("the user calls the widget preview API")
    public void sendWidgetPreviewRequest() {
        WidgetPreviewData widgetPreviewData = WidgetPreviewDataFactory
                .createWidgetPreviewData(widgetType);
        widgetApiClient
                .sendWidgetPreviewRequest(widgetPreviewData);
    }

    @And("^response field ([^\"]*) contains:")
    public void verifyResponseFieldContains(String name, DataTable dataTable) {
        String fieldName = scenarioContext.getVariable(name);
        Map<String, Integer> fieldMap = dataTable.asMap(String.class, Integer.class);
        fieldMap.forEach((field, value) ->
                widgetApiClient.verifyResponseField(fieldName + field, value.toString()));
    }
}
