package com.blog.controllers;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    //CREATE -> POST
    @PostMapping("/user/{userId}/category/{categoryId}/posts/")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    //UPDATE -> PUT
    @PutMapping("/posts/{postId}/")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.ACCEPTED);
    }

    //DELETE -> DELETE
    @DeleteMapping("/posts/{postId}/")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return  new ResponseEntity<>(new ApiResponse("Post successfully deleted for Id "+postId, Boolean.TRUE), HttpStatus.OK);
    }

    //GETById -> GET
    @GetMapping("/posts/{postId}/")
    public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostByPostId(postId);
        return new ResponseEntity<>(postDto, HttpStatus.FOUND);
    }

    //GetAll -> GET
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize){
        PostResponse postDtos = this.postService.getAllPosts(pageNumber, pageSize);
        return new ResponseEntity<>(postDtos, HttpStatus.FOUND);
    }

    //GetByUserId -> GET
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUserId(@PathVariable Integer userId,
                                                          @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                          @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize){
        PostResponse posts = this.postService.getAllPostsByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<>(posts, HttpStatus.FOUND);
    }

    //GetByCategoryId -> GET
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
                                                            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize){
        PostResponse postDtos = this.postService.getAllPostsByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(postDtos, HttpStatus.FOUND);
    }

}
