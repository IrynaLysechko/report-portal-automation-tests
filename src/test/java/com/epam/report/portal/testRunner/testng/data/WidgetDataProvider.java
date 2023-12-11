package com.epam.report.portal.testRunner.testng.data;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Collectors;

import static com.epam.report.portal.factory.data.WidgetPreviewDataFactory.*;
import static com.epam.report.portal.utils.Constants.BASE_RESOURCES_PATH;

public class WidgetDataProvider {

    @DataProvider(parallel = true)
    public Object[] widgetIdProvider() {
        return new Object[]{137217, 137218, 137219, 137220, 137221};
    }

    @DataProvider(parallel = true)
    public Object[][] widgetIdAndNameProvider() {
        return new Object[][]{
                {137217, "LAUNCH STATISTICS AREA"},
                {137218, "LAUNCH STATISTICS BAR"},
                {137219, "INVESTIGATED PERCENTAGE OF LAUNCHES"},
                {137220, "TEST CASES GROWTH TREND CHART"},
                {137221, "OVERALL STATISTICS PANEL"}
        };
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> csvDataProvider() throws IOException, CsvException {
        return new CSVReader(new FileReader(BASE_RESOURCES_PATH + "testRunner/failed_status_code.csv"))
                .readAll()
                .stream()
                .filter(line -> line.length == 2)
                .map(line -> new Object[]{line[0], Integer.parseInt(line[1].trim())})
                .collect(Collectors.toList())
                .iterator();
    }

    @DataProvider(parallel = true)
    public Object[][] widgetPreviewProvider() {
        return new Object[][]{
                {createFlakyTestCasesWidget()},
                {createLineChartWidget()},
                {createOverallStatisticsWidget()},
                {createStatisticTrendAreaWidget()},
                {createStatisticTrendBarWidget()}
        };
    }
}
