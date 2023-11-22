package com.epam.report.portal.test.api.data;

import org.testng.annotations.DataProvider;

import static com.epam.report.portal.factory.data.WidgetPreviewDataFactory.*;

public class DataProviderAPI {

    @DataProvider(parallel = true)
    public static Object[] widgetIdProvider() {
        return new Object[]{
                137217, 137218, 137219, 137220, 137221};
    }

    @DataProvider(parallel = true)
    public static Object[][] widgetIdAndNameProvider() {
        return new Object[][]{
                {137217, "LAUNCH STATISTICS AREA"},
                {137218, "LAUNCH STATISTICS BAR"},
                {137219, "INVESTIGATED PERCENTAGE OF LAUNCHES"},
                {137220, "TEST CASES GROWTH TREND CHART"},
                {137221, "OVERALL STATISTICS PANEL"}
        };
    }

    @DataProvider(parallel = true)
    public static Object[][] widgetPreviewProvider() {
        return new Object[][]{
                {createFlakyTestCasesWidget()},
                {createLineChartWidget()},
                {createOverallStatisticsWidget()},
                {createStatisticTrendAreaWidget()},
                {createStatisticTrendBarWidget()}
        };
    }
}
