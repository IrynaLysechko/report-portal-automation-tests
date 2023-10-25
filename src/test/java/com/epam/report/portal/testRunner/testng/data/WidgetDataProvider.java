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
        return new Object[]{2, 3, 4, 5, 6};
    }

    @DataProvider(parallel = true)
    public Object[][] widgetIdAndNameProvider() {
        return new Object[][]{
                {2, "LAUNCH STATISTICS AREA"},
                {3, "LAUNCH STATISTICS BAR"},
                {4, "INVESTIGATED PERCENTAGE OF LAUNCHES"},
                {5, "TEST CASES GROWTH TREND CHART"},
                {6, "OVERALL STATISTICS PANEL"}
        };
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> csvDataProvider() throws IOException, CsvException {
        return new CSVReader(new FileReader(BASE_RESOURCES_PATH + "failed_status_code.csv"))
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
