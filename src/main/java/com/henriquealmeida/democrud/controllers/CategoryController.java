package com.henriquealmeida.democrud.controllers;

import com.henriquealmeida.democrud.dto.response.CategoryResponseDTO;
import com.henriquealmeida.democrud.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Categories data endpoint", description = "REST service for searching categories of products data")
@RestController
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE, headers = "Bearer=token")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Return all categories from products",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully return all categories from products",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryResponseDTO.class)))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll().stream()
                .map(category -> super.convertToType(category, CategoryResponseDTO.class))
                .toList());
    }

    @Operation(
            summary = "Search one category by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully return category by id",
                            content = @Content(schema = @Schema(implementation = CategoryResponseDTO.class))
                    )
            }
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> finById(@PathVariable Long id) {
        return ResponseEntity.ok().body(convertToType(categoryService.findById(id), CategoryResponseDTO.class));
    }
}
