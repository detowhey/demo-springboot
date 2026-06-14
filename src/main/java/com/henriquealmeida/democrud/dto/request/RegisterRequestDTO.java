package com.henriquealmeida.democrud.dto.request;

import com.henriquealmeida.democrud.domain.enu.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "The login property cannot be empty")
        @Size(min = 5, message = "The login property cannot be less than 5 characters")
        String login,
        @NotBlank(message = "The password property cannot be empty")
        @Size(min = 5, message = "The password property cannot be less than 5 characters")
        String password,
        @NotBlank(message = "The property cannot be null")
        UserRole role
) {
}
