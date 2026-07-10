package com.API.eCommerce.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.eCommerce.Exceptions.AlreadyExistException;
import com.API.eCommerce.Exceptions.ResourceNotFoundException;
import com.API.eCommerce.model.Category;
import com.API.eCommerce.response.ApiResponse;
import com.API.eCommerce.service.Category.iCategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final iCategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();

            return ResponseEntity.ok(new ApiResponse("Found!", categories));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: ", INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category name) {
        try {
            Category category = categoryService.addCategory(name);

            return ResponseEntity.ok(new ApiResponse("Success", category));
        } catch (AlreadyExistException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/category/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try{
            categoryService.updateCategory(category, id);

            return ResponseEntity.ok(new ApiResponse("Update successfull", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoryById(id);

            return ResponseEntity.ok(new ApiResponse("Found", category));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {
        try {
            Category category = categoryService.getCategoryByName(name);

            return ResponseEntity.ok(new ApiResponse("Found", category));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/category/delete/{name}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategory(id);

            return ResponseEntity.ok(new ApiResponse("Found", null));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
