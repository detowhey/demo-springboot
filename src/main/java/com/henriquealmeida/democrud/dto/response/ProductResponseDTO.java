package com.henriquealmeida.democrud.dto.response;

import java.util.Set;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        Double price,
        String imgUrl,
        Set<CategoryResponseDTO> categories,
        Set<OrderItemResponseDTO> items
) {
}
