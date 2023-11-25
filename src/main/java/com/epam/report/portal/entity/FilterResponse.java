package com.epam.report.portal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FilterResponse {

    @JsonProperty("content")
    private List<String> content;

    @JsonProperty("page")
    private Page page;

    @Data
    public static class Page {

        @JsonProperty("number")
        private int number;

        @JsonProperty("size")
        private int size;

        @JsonProperty("totalElements")
        private int totalElements;

        @JsonProperty("totalPages")
        private int totalPages;
    }
}
