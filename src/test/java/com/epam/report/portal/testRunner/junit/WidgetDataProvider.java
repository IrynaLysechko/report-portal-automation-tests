package com.epam.report.portal.testRunner.junit;

import java.util.stream.Stream;

public class WidgetDataProvider {

    public static Stream<Integer> widgetIdProvider() {
        return Stream.of(130633,
                130632,
                130631,
                130630,
                130629,
                130628,
                130627,
                130626,
                130625,
                130624,
                130623,
                130622);
    }
}
