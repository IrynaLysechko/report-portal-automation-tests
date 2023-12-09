package com.epam.report.portal.ui.pages;

import com.epam.report.portal.factory.driver.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class AbstractPage {

    public AbstractPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public AbstractPage openPage(String url) {
        DriverManager.getDriver().get(url);
        return this;
    }

}
