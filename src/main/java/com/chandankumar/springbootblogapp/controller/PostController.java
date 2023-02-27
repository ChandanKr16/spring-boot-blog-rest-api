package com.chandankumar.springbootblogapp.controller;

import com.chandankumar.springbootblogapp.dto.PostDto;
import com.chandankumar.springbootblogapp.model.PostResponse;
import com.chandankumar.springbootblogapp.service.PostService;
import com.chandankumar.springbootblogapp.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/api/posts/")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable("id") Long id){
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.POST_DEFAULT_PAGE_NO, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.POST_DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.POST_DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.POST_DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id){
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<List<PostDto>> findByCategoryId(@PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(postService.getPostsByCategoryId(categoryId));
    }

    @GetMapping("search")
    public ResponseEntity<List<PostDto>> searchPostsByTitle(@RequestParam("title") String title){
        return ResponseEntity.ok(postService.searchPostsByTitle(title));
    }


}
