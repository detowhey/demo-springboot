package com.henriquealmeida.democrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henriquealmeida.democrud.entities.Category;
import com.henriquealmeida.democrud.entities.Product;

//<TypeEntity, KeyEntity>
public interface ProductRepository extends JpaRepository<Product, Long> {

}
