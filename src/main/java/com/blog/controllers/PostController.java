package com.blog.controllers;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import static com.blog.config.AppConstants.*;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = PAGE_NUMBER_STRING, defaultValue = PAGE_NUMBER_VALUE, required = false) Integer pageNumber,
                                                    @RequestParam(value = PAGE_SIZE_STRING, defaultValue = PAGE_SIZE_VALUE, required = false) Integer pageSize,
                                                    @RequestParam(value = SORT_BY_STRING, defaultValue = SORT_BY_VALUE, required = false) String sortBy,
                                                    @RequestParam(value = SORT_DIR_STRING, defaultValue = SORT_DIR_VALUE, required = false) String sortDir){
        PostResponse postDtos = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postDtos, HttpStatus.FOUND);
    }

    //GetByUserId -> GET
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostsByUserId(@PathVariable Integer userId,
                                                         @RequestParam(value = PAGE_NUMBER_STRING, defaultValue = PAGE_NUMBER_VALUE, required = false) Integer pageNumber,
                                                         @RequestParam(value = PAGE_SIZE_STRING, defaultValue = PAGE_SIZE_VALUE, required = false) Integer pageSize){
        PostResponse posts = this.postService.getAllPostsByUser(userId, pageNumber, pageSize);
        return new ResponseEntity<>(posts, HttpStatus.FOUND);
    }

    //GetByCategoryId -> GET
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
                                                           @RequestParam(value = PAGE_NUMBER_STRING, defaultValue = PAGE_NUMBER_VALUE, required = false) Integer pageNumber,
                                                           @RequestParam(value = PAGE_SIZE_STRING, defaultValue = PAGE_SIZE_VALUE, required = false) Integer pageSize){
        PostResponse postDtos = this.postService.getAllPostsByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(postDtos, HttpStatus.FOUND);
    }

    @GetMapping("/posts/title/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable("keyword") String keyword){
        List<PostDto> posts = this.postService.searchPosts(keyword);
        return new ResponseEntity<>(posts, HttpStatus.FOUND);
    }

    @PutMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
                                                   @PathVariable Integer postId) throws IOException {

        PostDto postDto = this.postService.getPostByPostId(postId);

        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);

    }

    //method to serve files
    @GetMapping(value = "/posts/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }
}
