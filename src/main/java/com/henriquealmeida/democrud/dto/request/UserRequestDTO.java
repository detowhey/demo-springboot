package com.henriquealmeida.democrud.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record UserRequestDTO(
        @NotEmpty(message = "The name property is not empty")
        String name,
        @NotEmpty(message = "The email property is not empty")
        @Email(message = "Invalid email")
        String email,
        @Nullable
        String phone,
        @NotEmpty(message = "The email property is not empty")
        @Length(min = 5, message = "The password can contain at least 5 characters")
        String password
) {
}
