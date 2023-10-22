package com.henriquealmeida.democrud.dto.response;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Order item", description = "The PK id of the Order Item")
public record OrderItemPkResponseDTO(
        @Schema(description = "The linked order")
        OrderResponseDTO order,
        @Schema(description = "The linked product")
        ProductResponseDTO product
) {
}
