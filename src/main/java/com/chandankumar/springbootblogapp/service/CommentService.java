package com.chandankumar.springbootblogapp.service;

import com.chandankumar.springbootblogapp.dto.CommentDto;
import com.chandankumar.springbootblogapp.model.Comment;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentById(long postId, long commentId);

    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);

    void deleteComment(long postId, long commentId);

}
