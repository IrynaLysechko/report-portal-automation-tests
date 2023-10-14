package com.epam.report.portal.layers.core.listeners;

import com.epam.report.portal.layers.core.logging.LoggerManager;
import com.epam.report.portal.layers.core.report.ReportManager;
import com.epam.report.portal.layers.core.config.AppConfiguration;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.extern.slf4j.Slf4j;
import org.testng.*;

import static com.epam.report.portal.layers.core.config.AppConfiguration.*;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static io.restassured.mapper.ObjectMapperType.GSON;

@Slf4j
public class TestListener implements IReporter, ITestListener, ISuiteListener, IInvokedMethodListener {

    @Override
    public void onStart(ITestContext context) {

        AppConfiguration.setReportPortalProperties();

        LoggerManager loggerManager = LoggerManager.getLoggerManager();
        loggerManager.setApplicationLogger(getLoggingTool());
        loggerManager.getLogger().setLogLevel();

        ReportManager reportManager = ReportManager.getReportManager();
        reportManager.setApplicationReport(getReportTool());
        reportManager.getReportAttachment().attachEnvironmentInfoToReport();

        log.info("Run tests for {}", context.getSuite().getName());

        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig(GSON));
        RestAssured.filters(new AllureRestAssured());
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
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
}

