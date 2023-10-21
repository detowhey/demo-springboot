package com.henriquealmeida.democrud.dto.response;

public record OrderItemResponseDTO(
        OrderItemPkResponseDTO id,
        Integer quantity,
        Double price
) {
}
