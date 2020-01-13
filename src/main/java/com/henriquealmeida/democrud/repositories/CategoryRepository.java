package com.henriquealmeida.democrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henriquealmeida.democrud.entities.Category;

//<TypeEntity, KeyEntity>
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
