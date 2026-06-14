package com.henriquealmeida.democrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henriquealmeida.democrud.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
