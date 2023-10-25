package com.epam.report.portal.testRunner.junit.data;

import com.epam.report.portal.entity.WidgetPreviewData;

import java.util.stream.Stream;

import static com.epam.report.portal.factory.data.WidgetPreviewDataFactory.*;

public class WidgetDataProvider {

    public static Stream<WidgetPreviewData> widgetPreviewDataProvider() {
        return Stream.of(
                createFlakyTestCasesWidget(),
                createLineChartWidget(),
                createOverallStatisticsWidget(),
                createStatisticTrendAreaWidget(),
                createStatisticTrendBarWidget()
        );
    }
}
