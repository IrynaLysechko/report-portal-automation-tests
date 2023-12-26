package com.epam.report.portal.listeners.testng;

import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.integration.slack.SlackApiClient;
import com.epam.report.portal.logging.LoggerManager;
import com.epam.report.portal.report.ReportManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.extern.slf4j.Slf4j;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.epam.report.portal.config.AppConfiguration.getLoggingTool;
import static com.epam.report.portal.config.AppConfiguration.getReportTool;
import static io.restassured.mapper.ObjectMapperType.GSON;

@Slf4j
public class TestListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        log.info("Running tests using TestNG Test Runner");
        AppConfiguration.setReportPortalProperties();
        setUpLogger();
        setUpReport();
        configureRestAssured();
        new SlackApiClient()
                .sendNotificationToSlackChannel("Test suite [" + suite.getName() + "] has been started");
    }

    @Override
    public void onFinish(ISuite suite) {
        new SlackApiClient()
                .sendNotificationToSlackChannel("Test suite [" + suite.getName() + "] has been finished");
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("TEST {} STARTED", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.error("TEST {} SKIPPED due to {}", result.getMethod().getMethodName(), result.getThrowable());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("TEST {} PASSED", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("TEST {} FAILED", result.getMethod().getMethodName());
        ReportManager.getReportManager().getReportAttachment()
                .attachScreenShootToReport();
    }

    private void setUpLogger() {
        LoggerManager loggerManager = LoggerManager.getLoggerManager();
        loggerManager.setApplicationLogger(getLoggingTool());
        loggerManager.getLogger().setLogLevel();
    }

    private void setUpReport() {
        ReportManager reportManager = ReportManager.getReportManager();
        reportManager.setApplicationReport(getReportTool());
        reportManager.getReportAttachment().attachEnvironmentInfoToReport();
    }

    private void configureRestAssured() {
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig(GSON));
        if (AppConfiguration.getReportTool().equals("allure")) {
            RestAssured.filters(new AllureRestAssured());
        }
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

}

