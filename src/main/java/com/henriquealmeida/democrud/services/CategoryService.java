package com.henriquealmeida.democrud.services;

import java.util.List;

import com.henriquealmeida.democrud.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henriquealmeida.democrud.domain.Category;
import com.henriquealmeida.democrud.repositories.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
