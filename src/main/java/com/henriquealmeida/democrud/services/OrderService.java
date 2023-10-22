package com.henriquealmeida.democrud.services;

import java.util.List;

import com.henriquealmeida.democrud.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henriquealmeida.democrud.domain.Order;
import com.henriquealmeida.democrud.repositories.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
