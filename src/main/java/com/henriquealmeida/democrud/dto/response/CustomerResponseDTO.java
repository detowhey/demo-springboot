package com.henriquealmeida.democrud.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "Customer", description = "The customers data")
public record CustomerResponseDTO(
        @Schema(description = "The id of customer", example = "123")
        Long id,
        @Schema(description = "The name of customer", example = "Joe")
        String name,
        @Schema(description = "The email of customer", example = "joe@email.com")
        String email,
        @Schema(description = "The phone of customer", example = "5551994749194")
        String phone,
        @Schema(description = "The list of orders of customer", example = "Coca-cola, Water, Oat meal, Bread")
        List<OrderResponseDTO> orders
) {
}
