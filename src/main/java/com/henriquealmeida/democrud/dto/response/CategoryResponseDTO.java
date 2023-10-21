package com.henriquealmeida.democrud.dto.response;

import java.util.Set;

public record CategoryResponseDTO(
        Long id,
        String name,
        Set<ProductResponseDTO> products
) {
}
