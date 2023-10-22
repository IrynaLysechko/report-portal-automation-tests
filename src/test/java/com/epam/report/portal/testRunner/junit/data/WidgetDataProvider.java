package com.epam.report.portal.testRunner.junit.data;

import java.util.stream.Stream;

public class WidgetDataProvider {

    public static Stream<Integer> widgetIdProvider() {
        return Stream.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
    }
}
