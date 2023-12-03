package com.epam.report.portal.ui.pages;

import com.epam.report.portal.factory.driver.DriverManager;
import org.openqa.selenium.support.PageFactory;

public class AbstractPage {

    public AbstractPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
}
