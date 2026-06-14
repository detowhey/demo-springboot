package com.henriquealmeida.democrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.henriquealmeida.democrud.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
