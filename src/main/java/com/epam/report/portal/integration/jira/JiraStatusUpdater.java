package com.epam.report.portal.integration.jira;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;

public class JiraStatusUpdater implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        updateJiraStatus(method.getTestMethod().getConstructorOrMethod().getMethod(), testResult);
    }

    private void updateJiraStatus(Method testMethod, ITestResult testResult) {
        JiraTicketId jiraID = testMethod.getAnnotation(JiraTicketId.class);

        if (jiraID != null) {
            String status = (testResult.getStatus() == ITestResult.SUCCESS) ? "Passed" : "Failed";
            new JiraApiClient()
                    .updateJiraTicketStatus(jiraID.value(), status);
        }
    }
}