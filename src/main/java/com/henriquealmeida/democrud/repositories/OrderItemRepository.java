package com.henriquealmeida.democrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henriquealmeida.democrud.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
