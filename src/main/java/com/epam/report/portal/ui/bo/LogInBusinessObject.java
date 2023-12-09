package com.epam.report.portal.ui.bo;

import com.epam.report.portal.ui.pages.LogInPageObject;
import io.qameta.allure.Step;

public class LogInBusinessObject {

    private final LogInPageObject loginPage = new LogInPageObject();

    @Step
    public LogInBusinessObject logIn(String email, String password) {
        loginPage
                .setUserEmail(email)
                .setUserPassword(password)
                .clickLogInButton();
        return this;
    }
}