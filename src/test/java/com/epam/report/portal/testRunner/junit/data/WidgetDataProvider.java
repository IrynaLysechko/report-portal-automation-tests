package com.epam.report.portal.testRunner.junit.data;

import com.epam.report.portal.entity.WidgetPreviewData;

import java.util.List;
import java.util.stream.Stream;

public class WidgetDataProvider {

    public static Stream<Integer> widgetIdProvider() {
        return Stream.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
    }

    public static Stream<WidgetPreviewData> widgetPreviewDataProvider() {
        return Stream.of(
                WidgetPreviewData.builder()
                        .contentParameters(WidgetPreviewData.ContentParameters.builder()
                                .contentFields(List.of("string"))
                                .itemsCount(0)
                                .widgetOptions(WidgetPreviewData.WidgetOptions.builder().
                                        build())
                                .build())
                        .filterIds(List.of(0))
                        .widgetType("oldLineChart")
                        .build(),

                WidgetPreviewData.builder()
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
                        .build(),

                WidgetPreviewData.builder()
                        .contentParameters(WidgetPreviewData.ContentParameters.builder()
                                .contentFields(List.of("string"))
                                .itemsCount(1)
                                .widgetOptions(WidgetPreviewData.WidgetOptions.builder()
                                        .viewMode("donut")
                                        .build())
                                .build())
                        .filterIds(List.of(0))
                        .widgetType("overallStatistics")
                        .build(),

                WidgetPreviewData.builder()
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
                        .build(),

                WidgetPreviewData.builder()
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
                        .build());
    }
}
