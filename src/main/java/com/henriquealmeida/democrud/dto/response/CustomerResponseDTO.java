package com.henriquealmeida.democrud.dto.response;

public record CustomerResponseDTO(
        Long id,
        String name,
        String email,
        String phone,
        String password
) {
}
