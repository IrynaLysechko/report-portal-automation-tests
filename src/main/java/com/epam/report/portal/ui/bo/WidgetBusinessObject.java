package com.epam.report.portal.ui.bo;

import com.epam.report.portal.ui.pages.WidgetPageObject;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WidgetBusinessObject {

    private final WidgetPageObject widgetPage = new WidgetPageObject();

    @Step
    public WidgetBusinessObject addParameterToWidgetFilter(String filterName, String parameterName,
                                                           String parameterValue){
        widgetPage
                .clickEditWidgetFilterButton()
                .selectWidgetFilter(filterName)
                .hoverOverWidgetFilter(filterName)
                .clickEditFilterButton()
                .clickFilterSelector()
                .selectAdditionalAttribute(parameterName)
                .setAttributeValueToInput(parameterValue)
                .clickSubmitButton();
        log.info("Added parameters {}:{} to filter with name {}", parameterName, parameterValue, parameterValue);
        return this;
    }

    @Step
    public void closeWidgetModalWindow() {
        widgetPage.clickCloseModalWindowsButton();
        log.info("Closed modal window");
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
    public void updateWidgetView(String widgetView) {
        widgetPage
                .changeWidgetView(widgetView)
                .clickSaveButton();
    }
}
