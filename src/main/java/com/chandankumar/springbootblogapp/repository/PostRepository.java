package com.chandankumar.springbootblogapp.repository;

import com.chandankumar.springbootblogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategoryId(Long categoryId);

    @Query("SELECT p from Post p WHERE p.title LIKE CONCAT('%', :title, '%')")
    List<Post> findPostsByTitle(String title);

}
