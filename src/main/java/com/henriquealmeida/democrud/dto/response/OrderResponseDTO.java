package com.henriquealmeida.democrud.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.Set;

@Schema(name = "Order", description = "The data of orders")
public record OrderResponseDTO(
        @Schema(description = "The id of order", example = "123")
        Long id,
        @Schema(description = "The date of order", example = "22/10/2023")
        Instant moment,
        @Schema(description = "The status of order", example = "3")
        Integer orderStatus,
        @Schema(description = "The items of order", example = "Coca-cola, Water, Bread, Cookies")
        Set<OrderItemResponseDTO> items,
        @Schema(description = "The customer of order")
        CustomerResponseDTO customer,
        @Schema(description = "The payment of order")
        PaymentResponseDTO payment
) {
}
