package com.chandankumar.springbootblogapp.service.impl;

import com.chandankumar.springbootblogapp.dto.CommentDto;
import com.chandankumar.springbootblogapp.dto.PostDto;
import com.chandankumar.springbootblogapp.exception.ResourceNotFoundException;
import com.chandankumar.springbootblogapp.model.Category;
import com.chandankumar.springbootblogapp.model.Comment;
import com.chandankumar.springbootblogapp.model.Post;
import com.chandankumar.springbootblogapp.model.PostResponse;
import com.chandankumar.springbootblogapp.repository.CategoryRepository;
import com.chandankumar.springbootblogapp.repository.PostRepository;
import com.chandankumar.springbootblogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;


    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        Post newPost = postRepository.save(mapToEntity(postDto));
        return mapToDto(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();


        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> postList = posts.getContent();

        List<PostDto> content =  postList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());


        return new PostResponse(content,
                                posts.getNumber(),
                                posts.getSize(),
                                posts.getTotalElements(),
                                posts.getTotalPages(),
                                posts.isLast());
    }

    @Override
    public PostDto getPostById(Long id) {
        return mapToDto(postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id)));
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));


        post.setTitle(postDto.getTitle());
        post.setCategory(category);
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategoryId(Long categoryId) {

        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        return postRepository.findByCategoryId(categoryId)
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private PostDto mapToDto(Post post){

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(post, PostDto.class);

//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
//
//        Set<CommentDto> comments = post.getComments().stream()
//                                    .map(this::mapToCommentDto)
//                                    .collect(Collectors.toSet());
//
//        postDto.setComments(comments);
//        return postDto;
    }

    private Post mapToEntity(PostDto postDto){

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postDto, Post.class);

//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
//
//        return post;
    }

    private CommentDto mapToCommentDto(Comment comment){

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(comment, CommentDto.class);

//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
//
//        return commentDto;
    }
}
