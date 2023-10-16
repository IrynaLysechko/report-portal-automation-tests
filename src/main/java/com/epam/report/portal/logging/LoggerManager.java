package com.epam.report.portal.logging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerManager {

    private static LoggerManager loggerManager;
    private static Logger loggerConfig;

    private LoggerManager() {
    }

    public static LoggerManager getLoggerManager() {
        if (loggerManager == null) {
            loggerManager = new LoggerManager();
        }
        return loggerManager;
    }

    public void setApplicationLogger(String loggerType) {
        log.info("Logging tool is {}", loggerType);
        if ("logback".equalsIgnoreCase(loggerType)) {
            loggerConfig = new LogbackConfiguration();
        } else {
            throw new ExceptionInInitializerError("Missing configuration for logger type " + loggerType);
        }
    }

    public Logger getLogger() {
        return loggerConfig;
    }
}
