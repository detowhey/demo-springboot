package com.henriquealmeida.democrud.services;

import java.util.List;

import com.henriquealmeida.democrud.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henriquealmeida.democrud.domain.Category;
import com.henriquealmeida.democrud.repositories.CategoryRepository;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        log.info("Return all categories");
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        log.info("Find category by id" + id);
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
