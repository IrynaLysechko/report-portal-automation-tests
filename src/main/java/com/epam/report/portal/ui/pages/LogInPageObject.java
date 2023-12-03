package com.epam.report.portal.ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPageObject extends AbstractPage{

    @FindBy(name = "login")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "(//button[@type='submit'])")
    private WebElement logInButton;

    @Step
    public LogInPageObject setUserEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step
    public LogInPageObject setUserPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step
    public LogInPageObject clickLogInButton() {
        logInButton.click();
        return this;
    }
}
