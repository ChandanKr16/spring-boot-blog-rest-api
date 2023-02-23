package com.chandankumar.springbootblogapp.service;

import com.chandankumar.springbootblogapp.dto.CategoryDto;
import com.chandankumar.springbootblogapp.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);

}

