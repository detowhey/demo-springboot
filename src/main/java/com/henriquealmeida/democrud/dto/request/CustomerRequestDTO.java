package com.henriquealmeida.democrud.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CustomerRequestDTO(
        @NotBlank(message = "The name property is not empty")
        String name,
        @NotBlank(message = "The email property is not empty")
        @Email(message = "Invalid email")
        String email,
        @Nullable
        String phone,
        @NotBlank(message = "The password property is not empty")
        @Length(min = 5, message = "The password can contain at least 5 characters")
        String password
) {
}
