package com.henriquealmeida.democrud.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(name = "Category", description = "The categories of products")
public record CategoryResponseDTO(
        @Schema(description = "The id of category", example = "123")
        Long id,
        @Schema(description = "The name of the category", example = "food")
        String name,
        @Schema(description = "The products of the category", example = "Bread, tomato, soft drink")
        Set<ProductResponseDTO> products
) {
}
