package com.epam.report.portal.ui.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AddNewWidgetPageObject extends AbstractPage{

    private String widgetTypeItemsXpath = "//div[@class='widgetTypeItem__widget-type-item--pkO3L']//span[contains(@class, 'inputRadio__children-container')]//div";

    @Step
    public List<String> getAvailableWidgetTypesList() {
        List<String> widgetTypes = new ArrayList<>();
        findListByXpath(widgetTypeItemsXpath).forEach(element  ->
                widgetTypes.add(element.getText()));
        log.info("available widget types {}", widgetTypes);
        return widgetTypes;
    }
}
