package com.chandankumar.springbootblogapp.dto;

import com.chandankumar.springbootblogapp.model.Comment;
import lombok.*;

import java.util.Set;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private  String content;
    private Set<CommentDto> comments;

}
