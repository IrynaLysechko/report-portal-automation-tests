package com.epam.report.portal.layers.core.logging;

import ch.qos.logback.classic.Level;
import com.epam.report.portal.layers.core.config.AppConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.Locale;

@Slf4j
public class LogbackConfiguration implements Logger {

    @Override
    public  void setLogLevel() {
        String logLevel;
        String logLevelInSysProps = System.getProperty("log.level");
        if (logLevelInSysProps != null) {
            logLevel = logLevelInSysProps.toUpperCase(Locale.ROOT);
        } else {
            logLevel = AppConfiguration.getDefaultLogLever();
        }
        log.info("Log level is {}", logLevel);
        ch.qos.logback.classic.Logger rootLogger =
                (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.toLevel(logLevel));
    }
}
