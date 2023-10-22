package com.henriquealmeida.democrud.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(name = "Product", description = "The data of products")
public record ProductResponseDTO(
        @Schema(description = "The id of product", example = "123")
        Long id,
        @Schema(description = "The name of product", example = "Water")
        String name,
        @Schema(description = "The description of product", example = "1L water PET")
        String description,
        @Schema(description = "The price of product", example = "12.00")
        Double price,
        @Schema(description = "The url of image product", example = "https://url.com/image/product")
        String imgUrl,
        @Schema(description = "The categories the product belongs to", example = "Drink, Food, Bakery")
        Set<CategoryResponseDTO> categories,
        @Schema(description = "The items of the product", example = "Coca cola, Water, Bread")
        Set<OrderItemResponseDTO> items
) {
}
