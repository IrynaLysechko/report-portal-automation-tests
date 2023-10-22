package com.epam.report.portal.testRunner.testng;

import org.testng.annotations.DataProvider;

public class WidgetDataProvider {

    @DataProvider
    public Object[][] widgetIdProvider() {
        return new Object[][]{
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8},
                {9},
                {10},
                {11},
                {12},
                {13}
        };
    }
}
