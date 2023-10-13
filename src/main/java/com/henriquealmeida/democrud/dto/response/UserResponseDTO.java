package com.henriquealmeida.democrud.dto.response;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String phone,
        String password) {
}
