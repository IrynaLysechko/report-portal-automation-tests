package com.epam.report.portal.factory.data;

import com.epam.report.portal.entity.WidgetPreviewData;

import java.util.List;

public class WidgetPreviewDataFactory {

    public static WidgetPreviewData createLineChartWidget() {
        return WidgetPreviewData.builder()
                .contentParameters(WidgetPreviewData.ContentParameters.builder()
                        .contentFields(List.of("string"))
                        .itemsCount(0)
                        .widgetOptions(WidgetPreviewData.WidgetOptions.builder().build())
                        .build())
                .filterIds(List.of(0))
                .widgetType("oldLineChart")
                .build();
    }

    public static WidgetPreviewData createFlakyTestCasesWidget() {
        return WidgetPreviewData.builder()
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
                .build();
    }

    public static WidgetPreviewData createOverallStatisticsWidget() {
        return WidgetPreviewData.builder()
                .contentParameters(WidgetPreviewData.ContentParameters.builder()
                        .contentFields(List.of("string"))
                        .itemsCount(1)
                        .widgetOptions(WidgetPreviewData.WidgetOptions.builder()
                                .viewMode("donut")
                                .build())
                        .build())
                .filterIds(List.of(0))
                .widgetType("overallStatistics")
                .build();
    }

    public static WidgetPreviewData createStatisticTrendBarWidget() {
        return WidgetPreviewData.builder()
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
                .build();
    }

    public static WidgetPreviewData createStatisticTrendAreaWidget() {
        return WidgetPreviewData.builder()
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
                .build();
    }

}
