package com.epam.report.portal.test.ui;

import com.epam.report.portal.config.AppConfiguration;
import com.epam.report.portal.factory.driver.DriverManager;
import com.epam.report.portal.listeners.testng.TestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod
    public void getDriver() {
        DriverManager
                .getDriver()
                .get(AppConfiguration.getReportPortalUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void turnDown() {
        DriverManager
                .quitDriver();
    }
}
