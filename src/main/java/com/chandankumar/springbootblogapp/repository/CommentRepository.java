package com.chandankumar.springbootblogapp.repository;

import com.chandankumar.springbootblogapp.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    Page<Comment> findCommentsByPostId(long postId, Pageable pageable);

    //Comment findCommentByPostIdAndId(long postId, long commentId);

}
