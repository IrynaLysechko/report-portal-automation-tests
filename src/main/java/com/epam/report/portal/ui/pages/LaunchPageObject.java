package com.epam.report.portal.ui.pages;

import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.factory.driver.DriverManager;
import com.epam.report.portal.utils.Waits;
import lombok.extern.slf4j.Slf4j;

import static com.epam.report.portal.config.AppConfiguration.getProjectLaunchUIURI;

@Slf4j
public class LaunchPageObject extends AbstractPage{

    public LaunchPageObject waitUntilLaunchPageIsOpened() {
        Waits.waitUntilUrlContains(getProjectLaunchUIURI());
        return this;
    }

    public String getLaunchPageUrl() {
        String url = DriverManager.getDriver().getCurrentUrl();
        log.info("Launch url is {}", url);
        return url;
    }
}
