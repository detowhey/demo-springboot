package com.henriquealmeida.democrud.dto.response;

public record OrderItemPkResponseDTO(
        OrderResponseDTO order,
        ProductResponseDTO product
) {
}
