package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.dto.response.OrderResponseDTO;
import com.henriquealmeida.democrud.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController extends BaseController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        return ResponseEntity.ok().body(orderService.findAll().stream()
                .map(order -> super.convertToType(order, OrderResponseDTO.class))
                .toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(super.convertToType(orderService.findById(id), OrderResponseDTO.class));
    }
}
