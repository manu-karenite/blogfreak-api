package com.blogfreak.blog_freak_api.controller;

import com.blogfreak.blog_freak_api.dto.CreateBlogDTO;
import com.blogfreak.blog_freak_api.dto.GetLikesForBlogDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBlogDTO;
import com.blogfreak.blog_freak_api.entity.Blog;
import com.blogfreak.blog_freak_api.oas.schema.error.*;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessBlog;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessGetLikesForBlog;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessListOfAllBlogs;
import com.blogfreak.blog_freak_api.service.BlogServiceImpl;
import com.blogfreak.blog_freak_api.util.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogController {
    private final String getAllBlogsDescription = "**Operation** : Get all blogs existing in the application\n\n";
    private final String getBlogByBlogIdDescription =
            "**Operation** : Get a unique blog existing in the application identified by blogId\n\n";
    private final String createBlogDescription =
            "**Operation** : Create a new blog which gets mapped to blogger's account based on logged-in blogger\n\n";
    private final String deleteABlogByBlogIdDescription =
            "**Operation** : Delete a blog belonging to the current logged-in blogger. BlogId is required to uniquely identify the blog to be deleted from the blogger's account\n\n";
    private final String updateABlogByBlogIdDescription =
            "**Operation** : Update a blog belonging to the current logged-in blogger. BlogId is required to uniquely identify the blog to be updated from the blogger's account\n\n";
    private final String likeOrUnlikeABlogDescription =
            "**Operation** : Like or unlike an existing blog identified using blogId. If the logged-in blogger has not yet liked the blog, a new like will be added for the blog. If the blogger already has a like on the blog, like will be removed\n\n";
    private final String getLikesForABlogDescription =
            "**Operation** : Gets the like statistics for a blog using blogId\n\n";

    @Autowired
    private BlogServiceImpl blogServiceImpl;

    public BlogController(BlogServiceImpl blogService) {
        this.blogServiceImpl = blogService;
    }

    @GetMapping("/blogs")
    @Operation(
            operationId = "getAllBlogs",
            description = getAllBlogsDescription + Constant.OAS_READ_AUTH,
            summary = "Get all blogs existing in the application")
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
    public ResponseEntity<GlobalResponseEntity> getAllBlogs(
            @RequestParam(name = "categoryIds", required = false, defaultValue = "") String categoryIds,
            @RequestParam(name = "bloggerId", required = false, defaultValue = "") String bloggerId) {
        GlobalResponseEntity globalResponseEntity =
                new GlobalResponseEntity(HttpStatus.OK, blogServiceImpl.getAllBlogs(categoryIds, bloggerId));
        return new ResponseEntity<>(globalResponseEntity, HttpStatus.OK);
    }

    @GetMapping("/blogs/{blogId}")
    @Operation(
            operationId = "getBlogById",
            description = getBlogByBlogIdDescription + Constant.OAS_READ_AUTH,
            summary = "Get blog details by id")
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

    @PostMapping("/blogs")
    @Operation(
            operationId = "createBlog",
            description = createBlogDescription + Constant.OAS_WRITE_AUTH,
            summary = "Create a new blog")
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

    @DeleteMapping("/blogs/{blogId}")
    @Operation(
            operationId = "deleteBlog",
            description = deleteABlogByBlogIdDescription + Constant.OAS_DELETE_AUTH,
            summary = "Delete a blog by blogId")
    @Tag(name = "Blogs")
    @ApiResponse(
            responseCode = "401",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception401.class)))
    @ApiResponse(
            responseCode = "403",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception403.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "410",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlog.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> deleteBlogByBlogId(
            @Valid @NotNull @Size(min = 30) @PathVariable final String blogId, final Principal principal) {
        final Blog deletedBlog = this.blogServiceImpl.deleteBlogByblogId(blogId, principal.getName());
        return new ResponseEntity<>(new GlobalResponseEntity<>(HttpStatus.GONE, deletedBlog), HttpStatus.GONE);
    }

    @PatchMapping("/blogs/{blogId}")
    @Operation(
            operationId = "updateBlogByBlogId",
            description = updateABlogByBlogIdDescription + Constant.OAS_MANAGE_AUTH,
            summary = "Update an existing blog by blogId")
    @Tag(name = "Blogs")
    @ApiResponse(
            responseCode = "200",
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
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> updateBlogByBlogId(
            @Valid @NotNull @Size(min = 30) @PathVariable final String blogId,
            @RequestBody @Valid UpdateBlogDTO updateBlogDTO,
            final Principal principal) {
        final String bloggerId = principal.getName();
        this.blogServiceImpl.updateBlogByblogId(blogId, updateBlogDTO, bloggerId);
        final Blog refreshedBlog = this.blogServiceImpl.getBlogById(blogId);
        return new ResponseEntity<>(new GlobalResponseEntity<>(HttpStatus.OK, refreshedBlog), HttpStatus.OK);
    }

    @PatchMapping("/blogs/{blogId}/like-unlike")
    @Operation(
            operationId = "likeUnlikeABlogByBlogId",
            description = likeOrUnlikeABlogDescription + Constant.OAS_MANAGE_AUTH,
            summary = "Like/Unlike a blog by blogId")
    @Tag(name = "Blogs")
    @ApiResponse(
            responseCode = "200",
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
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> likeUnlikeABlogByBlogId(
            @Valid @NotNull @Size(min = 30) @PathVariable final String blogId, final Principal principal) {
        final String bloggerId = principal.getName();
        this.blogServiceImpl.likeUnlikeABlogByBlogId(blogId, bloggerId);
        final Blog refreshedBlog = this.blogServiceImpl.getBlogById(blogId);
        return new ResponseEntity<>(new GlobalResponseEntity<>(HttpStatus.OK, refreshedBlog), HttpStatus.OK);
    }

    @GetMapping("/blogs/{blogId}/likes")
    @Operation(
            operationId = "getLikesForBlog",
            description = getLikesForABlogDescription + Constant.OAS_READ_AUTH,
            summary = "Get Likes for a Blog")
    @Tag(name = "Blogs")
    @ApiResponse(
            responseCode = "200",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuccessGetLikesForBlog.class)))
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
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> getLikesForBlog(
            @Valid @NotNull @Size(min = 30) @PathVariable final String blogId) {
        GetLikesForBlogDTO getLikesForBlogDTO = this.blogServiceImpl.getLikesForBlog(blogId);
        return new ResponseEntity<>(new GlobalResponseEntity<>(HttpStatus.OK, getLikesForBlogDTO), HttpStatus.OK);
    }
}
