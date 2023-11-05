package com.epam.report.portal.bdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/bdd",
        glue = "com/epam/report/portal/bdd/steps",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        })
public class Runner extends AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
                return super.scenarios();
        }

}
