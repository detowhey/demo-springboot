package com.henriquealmeida.democrud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henriquealmeida.democrud.domain.Order;
import com.henriquealmeida.democrud.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok().body(orderService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.findById(id));
    }
}
