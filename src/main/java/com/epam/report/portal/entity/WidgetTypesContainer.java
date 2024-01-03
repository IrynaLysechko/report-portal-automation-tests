package com.epam.report.portal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WidgetTypesContainer {

    @JsonProperty("widgetTypes")
    private List<String> widgetTypes;
}
