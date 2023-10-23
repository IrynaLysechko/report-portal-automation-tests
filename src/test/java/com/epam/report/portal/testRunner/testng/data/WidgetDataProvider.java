package com.epam.report.portal.testRunner.testng.data;

import com.epam.report.portal.entity.WidgetPreviewData;
import org.testng.annotations.DataProvider;

import java.util.List;

public class WidgetDataProvider {

    @DataProvider
    public Object[][] widgetIdProvider() {
        return new Object[][]{
                {2}, {3}, {4}, {5}, {6}, {7},
                {8}, {9}, {10}, {11}, {12}, {13}
        };
    }

    @DataProvider
    public Object[][] widgetPreviewProvider() {
        return new Object[][]{

                {WidgetPreviewData.builder()
                        .contentParameters(WidgetPreviewData.ContentParameters.builder()
                                .contentFields(List.of("string"))
                                .itemsCount(0)
                                .widgetOptions(WidgetPreviewData.WidgetOptions.builder().
                                        build())
                                .build())
                        .filterIds(List.of(0))
                        .widgetType("oldLineChart")
                        .build()},

                {WidgetPreviewData.builder()
                        .contentParameters(WidgetPreviewData.ContentParameters.builder()
                                .contentFields(List.of("string"))
                                .itemsCount(5)
                                .widgetOptions(WidgetPreviewData.WidgetOptions.builder()
                                        .launchNameFilter("Demo Api Tests")
                                        .includeMethods(false)
                                        .build())
                                .build())
                        .filterIds(List.of(0))
                        .widgetType("flakyTestCases")
                        .build()},

                {WidgetPreviewData.builder()
                        .contentParameters(WidgetPreviewData.ContentParameters.builder()
                                .contentFields(List.of("string"))
                                .itemsCount(1)
                                .widgetOptions(WidgetPreviewData.WidgetOptions.builder()
                                        .viewMode("donut")
                                        .build())
                                .build())
                        .filterIds(List.of(0))
                        .widgetType("overallStatistics")
                        .build()},

                {WidgetPreviewData.builder()
                        .contentParameters(WidgetPreviewData.ContentParameters.builder()
                                .contentFields(List.of("string"))
                                .itemsCount(1)
                                .widgetOptions(WidgetPreviewData.WidgetOptions.builder()
                                        .zoom(false)
                                        .viewMode("bar")
                                        .build())
                                .build())
                        .filterIds(List.of(0))
                        .widgetType("statisticTrend")
                        .build()},

                {WidgetPreviewData.builder()
                        .contentParameters(WidgetPreviewData.ContentParameters.builder()
                                .contentFields(List.of("string"))
                                .itemsCount(1)
                                .widgetOptions(WidgetPreviewData.WidgetOptions.builder()
                                        .timeline("launch")
                                        .viewMode("area-spline")
                                        .zoom(false)
                                        .build())
                                .build())
                        .filterIds(List.of(0))
                        .widgetType("statisticTrend")
                        .build()}
        };
    }
}
