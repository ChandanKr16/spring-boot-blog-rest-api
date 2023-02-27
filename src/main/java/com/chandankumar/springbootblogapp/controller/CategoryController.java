package com.chandankumar.springbootblogapp.controller;

import com.chandankumar.springbootblogapp.dto.CategoryDto;
import com.chandankumar.springbootblogapp.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    @Operation(tags = {"Category Controller"})
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(tags = {"Category Controller"})
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){

        CategoryDto savedCategory = categoryService.addCategory(categoryDto);

        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("{categoryId}")
    @Operation(tags = {"Category Controller"})
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Long categoryId){
        return new ResponseEntity<>(categoryService.getCategory(categoryId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{categoryId}")
    @Operation(tags = {"Category Controller"})
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("categoryId") Long categoryId,
                                                      @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{categoryId}")
    @Operation(tags = {"Category Controller"})
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }


}
