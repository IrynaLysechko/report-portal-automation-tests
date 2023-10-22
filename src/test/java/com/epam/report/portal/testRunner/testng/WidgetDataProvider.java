package com.epam.report.portal.testRunner.testng;

import org.testng.annotations.DataProvider;

public class WidgetDataProvider {

    @DataProvider
    public Object[][] widgetIdProvider() {
        return new Object[][] {
                {130633},
                {130632},
                {130631},
                {130630},
                {130629},
                {130628},
                {130627},
                {130626},
                {130625},
                {130624},
                {130623},
                {130622}
        };
    }
}
