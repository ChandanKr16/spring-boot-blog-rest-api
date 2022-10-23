package com.chandankumar.springbootblogapp.repository;

import com.chandankumar.springbootblogapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentsByPostId(long postId);

    //Comment findCommentByPostIdAndId(long postId, long commentId);

}
