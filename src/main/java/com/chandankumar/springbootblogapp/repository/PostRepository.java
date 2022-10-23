package com.chandankumar.springbootblogapp.repository;

import com.chandankumar.springbootblogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
}
