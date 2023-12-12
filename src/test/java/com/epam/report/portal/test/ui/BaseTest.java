package com.epam.report.portal.test.ui;

import com.epam.report.portal.factory.driver.DriverManager;
import com.epam.report.portal.listeners.testng.TestListener;
import com.epam.report.portal.ui.bo.LogInBusinessObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import static com.epam.report.portal.config.AppConfiguration.*;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void getDriver() {
        DriverManager
                .getDriver()
                .get(getReportPortalUrl());
    }

    @BeforeMethod(dependsOnMethods = "getDriver", alwaysRun = true)
    public void logIn() {
        new LogInBusinessObject()
                .logIn(getUserName(), getUserPassword());
    }

    @AfterMethod(alwaysRun = true)
    public void turnDown() {
        DriverManager
                .quitDriver();
    }
}
