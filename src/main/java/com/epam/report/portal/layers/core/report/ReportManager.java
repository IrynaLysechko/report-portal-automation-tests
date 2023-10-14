package com.epam.report.portal.layers.core.report;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportManager {

    private static ReportManager reportManager;
    private ReportAttachment reportAttachment;

    public static ReportManager getReportManager() {
        if (reportManager == null) {
            reportManager = new ReportManager();
        }
        return reportManager;
    }

    public void setApplicationReport(String reportType) {
        log.info("Report tool is {}", reportType);
        if ("allure".equalsIgnoreCase(reportType)) {
            reportAttachment = new AllureAttachment();
        } else {
            throw new ExceptionInInitializerError("missing configuration for report type " + reportType);
        }
    }

    public ReportAttachment getReportAttachment() {
        return reportAttachment;
    }
}
