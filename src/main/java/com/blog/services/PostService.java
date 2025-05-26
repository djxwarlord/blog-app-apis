package com.blog.services;

import com.blog.payloads.PostDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostDto getPostByPostId(Integer postId);
    List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);
    List<PostDto> getAllPostsByUser(Integer userId, Integer pageNumber, Integer pageSize);
    List<PostDto> getAllPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);
    List<PostDto> searchPosts(String keyword);
}
