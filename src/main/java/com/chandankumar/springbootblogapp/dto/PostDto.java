package com.chandankumar.springbootblogapp.dto;

import com.chandankumar.springbootblogapp.model.Comment;
import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 3 letters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 3 letters")
    private String description;

    @NotEmpty
    @Size(min = 15, message = "Post content should have at least 3 letters")
    private  String content;

    private Set<CommentDto> comments;

}
