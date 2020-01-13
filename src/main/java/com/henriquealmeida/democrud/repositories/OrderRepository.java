package com.henriquealmeida.democrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henriquealmeida.democrud.entities.Order;

//<TypeEntity, KeyEntity>
public interface OrderRepository extends JpaRepository<Order, Long> {

}
