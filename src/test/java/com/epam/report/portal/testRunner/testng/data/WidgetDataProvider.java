package com.epam.report.portal.testRunner.testng.data;

import com.epam.report.portal.entity.WidgetPreviewData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.report.portal.utils.Constants.BASE_RESOURCES_PATH;

public class WidgetDataProvider {

    @DataProvider
    public Object[] widgetIdProvider() {
        return new Object[]{2, 3, 4, 5, 6};
    }

    @DataProvider
    public Object[][] widgetIdAndNameProvider() {
        return new Object[][]{
                {2, "LAUNCH STATISTICS AREA"},
                {3, "LAUNCH STATISTICS BAR"},
                {4, "INVESTIGATED PERCENTAGE OF LAUNCHES"},
                {5, "TEST CASES GROWTH TREND CHART"},
                {6, "OVERALL STATISTICS PANEL"}
        };
    }

    @DataProvider
    public Iterator<Object[]> csvDataProvider() throws IOException, CsvException {
        return new CSVReader(new FileReader(BASE_RESOURCES_PATH + "failed_status_code.csv"))
                .readAll()
                .stream()
                .filter(line -> line.length == 2)
                .map(line -> new Object[]{line[0], Integer.parseInt(line[1].trim())})
                .collect(Collectors.toList())
                .iterator();
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
