package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.domain.Product;
import com.henriquealmeida.democrud.dto.response.ProductResponseDTO;
import com.henriquealmeida.democrud.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Products data endpoint", description = "REST service for products operations")
@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController extends BaseController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Return all products",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully return all products",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductResponseDTO.class)))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        return ResponseEntity.ok().body(productService.findAll().stream()
                .map(order -> super.convertToType(order, ProductResponseDTO.class))
                .toList());
    }

    @Operation(
            summary = "Return product by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully return product by id",
                            content = @Content(schema = @Schema(implementation = ProductResponseDTO.class))
                    )
            }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(super.convertToType(productService.findById(id), ProductResponseDTO.class));
    }

    @Operation(
            summary = "Create a new product",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully create a product",
                            content = @Content(schema = @Schema(implementation = ProductResponseDTO.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ProductResponseDTO> insertNewProduct(ProductResponseDTO productResponseDTO) {
        Product product = super.convertToType(productResponseDTO, Product.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(super.convertToType(productService.insertProduct(product), ProductResponseDTO.class));
    }
}
