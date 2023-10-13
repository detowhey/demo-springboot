package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.domain.Product;
import com.henriquealmeida.democrud.dto.response.ProductResponseDTO;
import com.henriquealmeida.democrud.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok().body(productService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> insertNewProduct(ProductResponseDTO productResponseDTO) {
        Product product = this.convertToType(productResponseDTO, Product.class);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(this.convertToType(productService.insertProduct(product), ProductResponseDTO.class));
    }

    private <T> T convertToType(Object source, Class<T> resultClass) {
        return new ModelMapper().map(source, resultClass);
    }
}
