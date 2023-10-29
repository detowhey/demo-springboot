package com.henriquealmeida.democrud.services;

import java.util.List;

import com.henriquealmeida.democrud.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henriquealmeida.democrud.domain.Product;
import com.henriquealmeida.democrud.repositories.ProductRepository;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        log.info("Return all products");
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        log.info("Find product by id " + id);
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Product insertProduct(Product product) {
        log.info("Create a new product");
        return productRepository.save(product);
    }
}
