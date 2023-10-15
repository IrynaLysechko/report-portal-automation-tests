package com.epam.report.portal.utils;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class FileReader {

    @Step
    public String readFileFromTestResourcesToString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get("./src/test/resources/" + filePath)));
    }

}
