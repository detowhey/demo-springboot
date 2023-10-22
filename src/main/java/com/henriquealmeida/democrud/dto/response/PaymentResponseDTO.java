package com.henriquealmeida.democrud.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "Payment", description = "The payment of the order")
public record PaymentResponseDTO(
        @Schema(description = "The id of payment", example = "123")
        Long id,
        @Schema(description = "The date of payment", example = "22/10/2023")
        Instant moment,
        @Schema(description = "The order of payment")
        OrderResponseDTO order
) {
}
