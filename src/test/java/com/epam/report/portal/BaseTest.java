package com.epam.report.portal;

import com.epam.report.portal.layers.core.listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class BaseTest {

    @Test
    public void test() {

    }
}
