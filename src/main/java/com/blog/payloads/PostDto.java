package com.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;

    @NotEmpty
    @Size(max = 20, message = "Title cannot exceed 20 characters !!")
    private String title;

    @NotEmpty
    @Size(max = 20000, message = "Content cannot exceed 20K characters !!")
    private String content;

    private String imageName;
    private Date createdDate;
    private CategoryDto category;
    private UserDto user;
}
