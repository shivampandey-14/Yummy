package com.shivam.esd_assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record CustomerRequest(
        @NotNull(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @NotNull(message="Customer email is required")
        @Email(message = "Email must be in correct format")
        @JsonProperty("email")
        String email,

        @NotNull(message = "Password should be present")
        @NotEmpty(message = "Password should be present")
        @NotBlank(message = "Password should be present")
        @Size(min = 6, max = 12)
        @JsonProperty("password")
        String password
) {
}
