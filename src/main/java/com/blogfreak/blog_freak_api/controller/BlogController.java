package com.blogfreak.blog_freak_api.controller;

import com.blogfreak.blog_freak_api.dto.CreateBlogDTO;
import com.blogfreak.blog_freak_api.entity.Blog;
import com.blogfreak.blog_freak_api.oas.schema.error.*;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessBlog;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessListOfAllBlogs;
import com.blogfreak.blog_freak_api.service.BlogServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogController {
    @Autowired
    private BlogServiceImpl blogServiceImpl;

    public BlogController(BlogServiceImpl blogService) {
        this.blogServiceImpl = blogService;
    }

    @GetMapping("/blogs")
    @Operation(operationId = "getAllBlogs", description = "Get list of all blogs", summary = "Get list of all blogs")
    @ApiResponse(
            responseCode = "200",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuccessListOfAllBlogs.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @Tag(name = "Blogs")
    public ResponseEntity<GlobalResponseEntity> getAllBlogs() {
        GlobalResponseEntity globalResponseEntity =
                new GlobalResponseEntity(HttpStatus.OK, blogServiceImpl.getAllBlogs());
        return new ResponseEntity<>(globalResponseEntity, HttpStatus.OK);
    }

    @GetMapping("/blogs/{blogId}")
    @Operation(operationId = "getBlogById", description = "Get blog details by id", summary = "Get blog details by id")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlog.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @Tag(name = "Blogs")
    public ResponseEntity<GlobalResponseEntity> getBlogById(@PathVariable @NotNull @Size(min = 1) String blogId) {
        GlobalResponseEntity globalResponseEntity =
                new GlobalResponseEntity(HttpStatus.OK, blogServiceImpl.getBlogById(blogId));
        return new ResponseEntity<>(globalResponseEntity, HttpStatus.OK);
    }

    @PostMapping("/blog")
    @Operation(operationId = "createBlog", description = "Create a new blog", summary = "Create a new blog")
    @Tag(name = "Blogs")
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlog.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception400.class)))
    @ApiResponse(
            responseCode = "401",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception401.class)))
    @ApiResponse(
            responseCode = "403",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception403.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> createBlog(@Valid @RequestBody CreateBlogDTO createBlogDTO) {
        Blog blog = blogServiceImpl.createBlog(createBlogDTO);
        Blog refreshedBlog = blogServiceImpl.getBlogById(blog.getId());
        return new ResponseEntity<>(new GlobalResponseEntity<>(HttpStatus.CREATED, refreshedBlog), HttpStatus.CREATED);
    }
}
