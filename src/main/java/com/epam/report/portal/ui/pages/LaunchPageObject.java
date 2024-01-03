package com.epam.report.portal.ui.pages;

import com.epam.report.portal.utils.Waits;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.epam.report.portal.config.AppConfiguration.getProjectLaunchUIURI;

@Slf4j
public class LaunchPageObject extends AbstractPage{

    public LaunchPageObject waitUntilLaunchPageIsOpened() {
        Waits.waitUntilUrlContains(getProjectLaunchUIURI());
        return this;
    }

    @Step
    public String getLaunchPageUrl() {
        String url = driver.getCurrentUrl();
        log.info("Launch url is {}", url);
        return url;
    }
}
