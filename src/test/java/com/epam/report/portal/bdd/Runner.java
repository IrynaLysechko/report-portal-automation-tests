package com.epam.report.portal.bdd;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/bdd",
        glue = "com/epam/report/portal/bdd/steps",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        })
public class Runner extends AbstractTestNGCucumberTests {


}
