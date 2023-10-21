package com.henriquealmeida.democrud.dto.response;

import java.time.Instant;
import java.util.Set;

public record OrderResponseDTO(
        Long id,
        Instant moment,
        Integer orderStatus,
        Set<OrderItemResponseDTO> items,
        CustomerResponseDTO customer,
        PaymentResponseDTO payment
) {
}
