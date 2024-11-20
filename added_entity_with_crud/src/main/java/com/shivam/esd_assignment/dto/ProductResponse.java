package com.shivam.esd_assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponse(
        @JsonProperty("p_name")
        String productName
) {

}
