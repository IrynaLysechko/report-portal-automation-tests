package com.epam.report.portal.test.ui;

import com.epam.report.portal.factory.driver.DriverManager;
import com.epam.report.portal.listeners.testng.TestListener;
import com.epam.report.portal.ui.bo.LogInBusinessObject;
import com.epam.report.portal.ui.pages.DashboardPageObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.epam.report.portal.config.AppConfiguration.*;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod
    public void getDriver() {
        DriverManager
                .getDriver()
                .get(getReportPortalUrl());
    }

    @BeforeMethod(dependsOnMethods = "getDriver")
    public void logIn() {
        new LogInBusinessObject()
                .logIn(getUserName(), getUserPassword());
        new DashboardPageObject()
                .verifyUserIsLogIn();
    }

    @AfterMethod(alwaysRun = true)
    public void turnDown() {
        DriverManager
                .quitDriver();
    }
}
