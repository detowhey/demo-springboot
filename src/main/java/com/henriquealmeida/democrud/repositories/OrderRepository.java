package com.henriquealmeida.democrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henriquealmeida.democrud.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
