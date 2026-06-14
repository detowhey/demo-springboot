package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.dto.response.OrderResponseDTO;
import com.henriquealmeida.democrud.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Orders data endpoint", description = "REST service for  searching orders of customers")
@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController extends BaseController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
            summary = "Return all orders data",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully return all orders ",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrderResponseDTO.class)))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        return ResponseEntity.ok().body(orderService.findAll().stream()
                .map(order -> super.convertToType(order, OrderResponseDTO.class))
                .toList());
    }

    @Operation(
            summary = "Return one order by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully return order by id",
                            content = @Content(schema = @Schema(implementation = OrderResponseDTO.class))
                    )
            }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(super.convertToType(orderService.findById(id), OrderResponseDTO.class));
    }
}
