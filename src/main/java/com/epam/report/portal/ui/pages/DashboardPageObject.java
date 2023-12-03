package com.epam.report.portal.ui.pages;

import com.epam.report.portal.factory.driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPageObject extends AbstractPage {

    @FindBy(xpath = "//button[@type='button']/span[text()='Add new widget']")
    private WebElement addNewWidgetButton;

    public DashboardPageObject openDashboardPage() {
        DriverManager.getDriver()
                .get("https://reportportal.epam.com/ui/#irynal_personal/dashboard/137229");
        return this;
    }

    public DashboardPageObject clickAddNewWidgetButton() {
        addNewWidgetButton.click();
        return this;
    }
}
