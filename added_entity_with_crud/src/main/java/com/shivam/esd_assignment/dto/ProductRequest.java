package com.shivam.esd_assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;

public record ProductRequest(
        @JsonProperty("p_name")
        String productName,

        @Size(max = 100)
        @JsonProperty("p_desc")
        String productDesc,

        @NotNull(message = "Quantity is required")
        @JsonProperty("quantity")
        int quantity,

        @JsonProperty("brand")
        String brand,

        @JsonProperty("c_id")
        long categoryId,

        @JsonProperty("price")
        long price
)
{

}
