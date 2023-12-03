package com.epam.report.portal.ui.bo;

import com.epam.report.portal.factory.driver.DriverManager;
import com.epam.report.portal.ui.pages.LogInPageObject;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;

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

    public void verifyLogIn() throws InterruptedException {
        Thread.sleep(20000);
        Assertions.assertThat(DriverManager.getDriver().getCurrentUrl()).isEqualTo(
                "https://reportportal.epam.com/ui/#iryna_lysechko_personal/dashboard");
    }
}
