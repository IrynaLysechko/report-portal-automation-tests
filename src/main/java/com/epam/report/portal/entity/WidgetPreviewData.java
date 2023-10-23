package com.epam.report.portal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WidgetPreviewData {

    @JsonProperty("contentParameters")
    private ContentParameters contentParameters;

    @JsonProperty("filterIds")
    private List<Integer> filterIds;

    @JsonProperty("widgetType")
    private String widgetType;

    @Data
    @Builder
    public static class ContentParameters {
        @JsonProperty("contentFields")
        private List<String> contentFields;

        @JsonProperty("itemsCount")
        private int itemsCount;

        @JsonProperty("widgetOptions")
        private WidgetOptions widgetOptions;
    }

    @Data
    @Builder
    public static class WidgetOptions {
        @JsonProperty("launchNameFilter")
        private String launchNameFilter;

        @JsonProperty("includeMethods")
        private boolean includeMethods;

        @JsonProperty("viewMode")
        private String viewMode;

        @JsonProperty("zoom")
        private boolean zoom;

        @JsonProperty("timeline")
        private String timeline;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }
}

