package com.epam.report.portal.ui.bo;

import com.epam.report.portal.ui.pages.LogInPageObject;
import com.epam.report.portal.utils.Waits;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.epam.report.portal.config.AppConfiguration.getProjectDashboardUIURI;

@Slf4j
public class LogInBusinessObject {

    private final LogInPageObject loginPage = new LogInPageObject();

    @Step
    public LogInBusinessObject logIn(String email, String password) {
        loginPage
                .setUserEmail(email)
                .setUserPassword(password)
                .clickLogInButton();
        Waits.waitUntilUrlContains(getProjectDashboardUIURI());
        return this;
    }
}
