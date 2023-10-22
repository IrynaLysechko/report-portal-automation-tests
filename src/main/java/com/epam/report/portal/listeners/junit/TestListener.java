package com.epam.report.portal.listeners.junit;

import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.logging.LoggerManager;
import com.epam.report.portal.report.ReportManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;

import static com.epam.report.portal.config.AppConfiguration.getLoggingTool;
import static com.epam.report.portal.config.AppConfiguration.getReportTool;
import static io.restassured.mapper.ObjectMapperType.GSON;

@Slf4j
public class TestListener implements BeforeAllCallback, BeforeEachCallback, TestWatcher {

    @Override
    public void beforeAll(ExtensionContext context) {
        AppConfiguration.setReportPortalProperties();
        setUpLogger();
        setUpReport();
        configureRestAssured();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        log.info("Test started {}", extensionContext.getRequiredTestMethod().getName() );
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("Test Succeeded for {} ", context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info("Test Aborted for: {}, cause: {}", context.getDisplayName(), cause.getMessage());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info("Test Failed for: {}, cause: {}", context.getDisplayName(), cause.getMessage());
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
