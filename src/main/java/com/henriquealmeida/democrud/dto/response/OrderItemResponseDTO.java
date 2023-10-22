package com.henriquealmeida.democrud.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "The items of order")
public record OrderItemResponseDTO(
        @Schema(description = "The id of item", example = "123")
        OrderItemPkResponseDTO id,
        @Schema(description = "Quantity of items", example = "5")
        Integer quantity,
        @Schema(description = "The of item", example = "10.00")
        Double price
) {
}
