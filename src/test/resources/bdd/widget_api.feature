Feature: Widget API testing

  Background:
    Given a Widget API client

  Rule: API calls to retries widgets by id

    Scenario: Verify user user is able to receive all widget names
      When the user send request to receive all widget names
      Then the response status code is 200
      And the response contains following names:
        | LAUNCH STATISTICS AREA              |
        | LAUNCH STATISTICS BAR               |
        | INVESTIGATED PERCENTAGE OF LAUNCHES |
        | TEST CASES GROWTH TREND CHART       |
        | OVERALL STATISTICS PANEL            |
      But the response not contains following names:
        | Test Widget |
      And response field pages contains:
        | number        | 1  |
        | size          | 20 |
        | totalElements | 12 |
        | totalPages    | 1  |


    Scenario Outline: Verify user is able to receive widget by id with correct name
      When the user retrieves a widget by id <Widget ID>
      Then the response status code is <Status Code>
      And response field <Response Field> contains <Expected Value>

      Examples:
        | Widget ID | Status Code | Response Field | Expected Value                      |
        | 137217    | 200         | name           | LAUNCH STATISTICS AREA              |
        | 137218    | 200         | name           | LAUNCH STATISTICS BAR               |
        | 137219    | 200         | name           | INVESTIGATED PERCENTAGE OF LAUNCHES |
        | 137220    | 200         | name           | TEST CASES GROWTH TREND CHART       |
        | 137221    | 200         | name           | OVERALL STATISTICS PANEL            |


  Rule: API calls to widget review

    Scenario Outline: Verify user is able to get a widget preview
      And the user has a widget preview request for widget type <Widget Type>
      When the user calls the widget preview API
      Then the response status code is 200

      Examples:
        | Widget Type              |
        | flakyTestCasesWidget     |
        | lineChartWidget          |
        | overallStatisticsWidget  |
        | statisticTrendAreaWidget |
        | statisticTrendBarWidget  |



