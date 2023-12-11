package com.epam.report.portal.ui.pages;

import io.qameta.allure.Step;

public class LogInPageObject extends AbstractPage {

    private String emailInputName = "login";
    private String passwordInputName = "password";
    private String logInButtonXpath = "//button[@type='submit']";

    @Step
    public LogInPageObject setUserEmail(String email) {
        findByName(emailInputName)
                .sendKeys(email);
        return this;
    }

    @Step
    public LogInPageObject setUserPassword(String password) {
        findByName(passwordInputName)
                .sendKeys(password);
        return this;
    }

    @Step
    public LogInPageObject clickLogInButton() {
        click(logInButtonXpath);
        return this;
    }
}
