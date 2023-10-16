package com.henriquealmeida.democrud.dto.response;

import com.henriquealmeida.democrud.domain.Customer;
import com.henriquealmeida.democrud.domain.OrderItem;
import com.henriquealmeida.democrud.domain.Payment;

import java.time.Instant;
import java.util.Set;

public record OrderResponseDTO(
        Long id,
        Instant moment,
        Integer orderStatus,
        Set<OrderItem> items,
        Customer customer,
        Payment payment
) {
}
