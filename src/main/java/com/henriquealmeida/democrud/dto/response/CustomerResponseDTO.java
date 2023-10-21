package com.henriquealmeida.democrud.dto.response;

import java.util.List;

public record CustomerResponseDTO(
        Long id,
        String name,
        String email,
        String phone,
        String password,
        List<OrderResponseDTO> orders
) {
}
