package com.henriquealmeida.democrud.dto.response;

import java.time.Instant;

public record PaymentResponseDTO(
        Long id,
        Instant moment,
        OrderResponseDTO order
) {
}
