package com.epam.report.portal.report;

import com.epam.report.portal.config.AppConfiguration;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

@Slf4j
public class AllureAttachment implements ReportAttachment {

    @Override
    public void attachScreenShootToReport() {
        // TODO: Implement once browser driver logic will be added
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
