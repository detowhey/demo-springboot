package com.henriquealmeida.democrud.dto.response;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        Double price,
        String imgUrl
) {
}
