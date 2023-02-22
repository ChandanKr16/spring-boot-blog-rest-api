package com.chandankumar.springbootblogapp.repository;

import com.chandankumar.springbootblogapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
