package com.henriquealmeida.democrud.controllers;

import java.net.URI;
import java.util.List;

import com.henriquealmeida.democrud.domain.User;
import com.henriquealmeida.democrud.dto.response.ProductResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.henriquealmeida.democrud.domain.Product;
import com.henriquealmeida.democrud.services.ProductService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper = new ModelMapper();

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

        Product product = this.dtoToProduct(productResponseDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(this.productToDTO(productService.insertProduct(product)));
    }

    private ProductResponseDTO productToDTO(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    private Product dtoToProduct(ProductResponseDTO productResponseDTO) {
        return modelMapper.map(productResponseDTO, Product.class);
    }
}
