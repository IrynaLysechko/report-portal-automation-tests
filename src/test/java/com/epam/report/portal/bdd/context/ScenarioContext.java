package com.epam.report.portal.bdd.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static final Logger log = LoggerFactory.getLogger(ScenarioContext.class);
    private final Map<String, String> scenarioVariablesStore;

    public ScenarioContext() {
        scenarioVariablesStore = new HashMap<>();
    }

    public void addVariable(String key, String value) {
        if (scenarioVariablesStore.containsKey(key)) {
            throw new RuntimeException("Key " + key + " has been already defined in scenario store");
        }
        scenarioVariablesStore.put(key, value);
    }

    public String getVariable(String key) {
        String value = scenarioVariablesStore.get(key);
        if (value != null) {
            log.info("Scenario variable key: {} was found with value: {}", key, value);
            return value;
        } else {
            log.info("No scenario variable found for key: {}", key);
            return key;
        }
    }
}


