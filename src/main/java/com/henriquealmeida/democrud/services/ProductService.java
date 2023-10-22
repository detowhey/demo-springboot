package com.henriquealmeida.democrud.services;

import java.util.List;

import com.henriquealmeida.democrud.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henriquealmeida.democrud.domain.Product;
import com.henriquealmeida.democrud.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }
}
