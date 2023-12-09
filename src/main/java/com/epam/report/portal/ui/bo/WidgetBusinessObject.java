package com.epam.report.portal.ui.bo;

import com.epam.report.portal.ui.pages.WidgetPageObject;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WidgetBusinessObject {

    private WidgetPageObject widgetPage = new WidgetPageObject();

    @Step
    public WidgetBusinessObject addParameterToTestWidgetFilter(String parameterName, String parameterValue){
        widgetPage
                .clickEditWidgetFilterButton()
                .selectTestWidgetFilter()
                .hoverOverTestFilter()
                .clickEditTestFilterButton()
                .clickFilterSelector()
                .selectAdditionalAttribute(parameterName)
                .setAttributeValueToInput(parameterValue)
                .clickSubmitButton();
        return this;
    }

    @Step
    public void closeWidgetModalWindow() {
        widgetPage.clickCloseModalWindowsButton();
    }

    @Step
    public void updateWidgetName(String updatedWidgetName) {
        widgetPage
                .setWidgetNameToInput(updatedWidgetName)
                .clickSaveButton();
    }

    @Step
    public void updateWidgetDescription(String updatedWidgetDescription) {
        widgetPage
                .setWidgetDescriptionToInput(updatedWidgetDescription)
                .clickSaveButton();
    }

    @Step
    public void updateWidgetItemCount(String itemCount) {
        widgetPage
                .setWidgetItemsCountToInput(itemCount)
                .clickSaveButton();
    }

    @Step
    public void updateWidgetView() {
        widgetPage
                .changeWidgetView()
                .clickSaveButton();
    }
}
