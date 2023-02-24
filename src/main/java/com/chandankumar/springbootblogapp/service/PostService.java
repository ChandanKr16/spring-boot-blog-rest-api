package com.chandankumar.springbootblogapp.service;

import com.chandankumar.springbootblogapp.dto.PostDto;
import com.chandankumar.springbootblogapp.model.Post;
import com.chandankumar.springbootblogapp.model.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    void deletePostById(Long id);

    List<PostDto> getPostsByCategoryId(Long categoryId);

    List<PostDto> searchPostsByTitle(String title);



}
