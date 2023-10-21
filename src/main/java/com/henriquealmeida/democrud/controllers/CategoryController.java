package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.dto.response.CategoryResponseDTO;
import com.henriquealmeida.democrud.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll().stream()
                .map(category -> super.convertToType(category, CategoryResponseDTO.class))
                .toList());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(convertToType(categoryService.findById(id), CategoryResponseDTO.class));
    }
}
