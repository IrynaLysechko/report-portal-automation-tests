package com.epam.report.portal.report;

import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.factory.driver.DriverManager;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Slf4j
public class AllureAttachment implements ReportAttachment {

    @Override
    @Attachment(value = "Failed test screenshot", type = "image/png")
    public byte[] attachScreenShootToReport() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void attachEnvironmentInfoToReport() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("App name", AppConfiguration.getAppName())
                        .put("ReportPortal url", AppConfiguration.getReportPortalUrl())
                        .put("ReportPortal project name", AppConfiguration.getReportPortalProjectName())
                        .build(), System.getProperty("user.dir")
                        + "/build/allure-results/");
    }
}
