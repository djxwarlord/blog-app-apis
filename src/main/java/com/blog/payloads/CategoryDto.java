package com.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;

    @NotEmpty
    @Size(max = 20, message = "Title cannot exceed 20 characters !!")
    private String categoryTitle;

    @NotEmpty(message = "Description can't be empty !!")
    @Size(max = 20, message = "Description cannot exceed 20 characters !!")
    private String categoryDescription;
}
