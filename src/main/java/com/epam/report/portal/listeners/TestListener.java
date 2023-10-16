package com.epam.report.portal.listeners;

import com.epam.report.portal.logging.LoggerManager;
import com.epam.report.portal.report.ReportManager;
import com.epam.report.portal.config.AppConfiguration;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.extern.slf4j.Slf4j;
import org.testng.*;

import static com.epam.report.portal.config.AppConfiguration.*;
import static io.restassured.mapper.ObjectMapperType.GSON;

@Slf4j
public class TestListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        AppConfiguration.setReportPortalProperties();
        setUpLogger();
        setUpReport();
        configureRestAssured();
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

