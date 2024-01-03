package com.epam.report.portal.ui.bo;

import com.epam.report.portal.ui.pages.WidgetPageObject;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WidgetBusinessObject {

    private final WidgetPageObject widgetPage = new WidgetPageObject();

    @Step
    public WidgetBusinessObject changeWidgetFilter(String filterName){
        widgetPage
                .clickEditWidgetFilterButton()
                .selectWidgetFilter(filterName)
                .clickSubmitButton();
        log.info("Changed widget filter to {}", filterName);
        return this;
    }

    @Step
    public String getWidgetFilterName() {
        return widgetPage
                .getWidgetFilterName();
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

    @Step
    public String getWidgetItemCount() {
        return widgetPage
                .getWidgetItemCount();
    }

    @Step
    public String getInfoAboutFilter(String filterName) {
        String filterInfo =  widgetPage
                .getFilterInfo(filterName);
        log.info("Filter '{}' info: {}", filterName, filterInfo);
        return filterInfo;
    }
}
