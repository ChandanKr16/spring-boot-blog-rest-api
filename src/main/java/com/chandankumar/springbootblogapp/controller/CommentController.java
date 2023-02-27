package com.chandankumar.springbootblogapp.controller;

import com.chandankumar.springbootblogapp.dto.CommentDto;
import com.chandankumar.springbootblogapp.model.CommentResponse;
import com.chandankumar.springbootblogapp.service.CommentService;
import com.chandankumar.springbootblogapp.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId,
                                                   @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CommentResponse> getCommentsByPostId(@PathVariable("postId") long postId,
                                                               @RequestParam(value = "pageNo", defaultValue = AppConstants.COMMENT_DEFAULT_PAGE_NO, required = false) int pageNo,
                                                               @RequestParam(value = "pageSize", defaultValue = AppConstants.COMMENT_DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                                               @RequestParam(value = "sortBy", defaultValue = AppConstants.COMMENT_DEFAULT_SORT_BY, required = false) String sortBy,
                                                               @RequestParam(value = "sortDir", defaultValue = AppConstants.COMMENT_DEFAULT_SORT_DIR, required = false) String sortDir
                                                               ){

        return new ResponseEntity<>(commentService.getCommentsByPostId(postId, pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId,
                                                     @PathVariable("commentId") long commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }



    @PutMapping("{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId,
                                                    @PathVariable("commentId") long commentId,
                                                   @Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId,
                                                @PathVariable("commentId") long commentId) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment deleted");
    }



}
