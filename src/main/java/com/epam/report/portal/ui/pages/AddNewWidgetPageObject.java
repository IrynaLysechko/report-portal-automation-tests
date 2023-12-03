package com.epam.report.portal.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AddNewWidgetPageObject extends AbstractPage{

    @FindBy(xpath = "//div[@class='widgetTypeItem__widget-type-item--pkO3L']//span[contains(@class, 'inputRadio__children-container')]//div")
    private List<WebElement> widgetTypeItems;

    public List<String> getAvailableWidgetTypesList() {
        List<String> widgetTypes = new ArrayList<>();
        widgetTypeItems.forEach(element  ->
                widgetTypes.add(element.getText()));
        log.info("available widget types {}", widgetTypes);
        return widgetTypes;
    }
}
