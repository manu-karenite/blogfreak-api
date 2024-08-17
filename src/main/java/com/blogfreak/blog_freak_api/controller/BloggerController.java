package com.blogfreak.blog_freak_api.controller;

import com.blogfreak.blog_freak_api.dto.CreateBloggerDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBloggerDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBloggerPasswordDTO;
import com.blogfreak.blog_freak_api.entity.Blogger;
import com.blogfreak.blog_freak_api.oas.schema.error.*;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessBlogger;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessListOfAllBloggers;
import com.blogfreak.blog_freak_api.service.BloggerService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class BloggerController {
    @Autowired
    BloggerService bloggerService;

    public BloggerController(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @GetMapping("/bloggers")
    @Operation(
            operationId = "getAllBloggers",
            description = "Get list of all bloggers",
            summary = "Get list of all bloggers")
    @ApiResponse(
            responseCode = "200",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuccessListOfAllBloggers.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @Tag(name = "Bloggers")
    public ResponseEntity<GlobalResponseEntity> getAllBloggers() {
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(HttpStatus.OK, bloggerService.getAllBloggers()), HttpStatus.OK);
    }

    @GetMapping("/bloggers/{bloggerId}")
    @Operation(
            operationId = "getBloggerById",
            description = "Get blogger details by id",
            summary = "Get blogger details by id")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlogger.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @Tag(name = "Bloggers")
    public ResponseEntity<GlobalResponseEntity> getBloggerById(@PathVariable @NotNull @Size(min = 1) String bloggerId) {
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(HttpStatus.OK, bloggerService.getBloggerById(bloggerId)), HttpStatus.OK);
    }

    @PostMapping("/bloggers")
    @Operation(operationId = "createBlogger", description = "Create a new blogger", summary = "Create a new blogger")
    @Tag(name = "Bloggers")
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlogger.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception400.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> createBlogger(@Valid @RequestBody CreateBloggerDTO createBloggerDTO) {
        Blogger blogger = bloggerService.createBlogger(createBloggerDTO);
        Blogger refreshedBlogger = bloggerService.getBloggerById(blogger.getId());
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(HttpStatus.CREATED, refreshedBlogger), HttpStatus.CREATED);
    }

    @PatchMapping("/blogger")
    @Operation(
            operationId = "updateBlogger",
            description = "Update an existing blogger",
            summary = "Update an existing blogger")
    @Tag(name = "Bloggers")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlogger.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception400.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> updateBlogger(
            @RequestBody @Valid UpdateBloggerDTO updateBloggerDTORequest, final Principal principal) {
        final String bloggerId = principal.getName();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(
                        HttpStatus.OK, bloggerService.updateBlogger(updateBloggerDTORequest, bloggerId)),
                HttpStatus.OK);
    }

    @PatchMapping("/blogger/password")
    @Operation(
            operationId = "updateBloggerPassword",
            description = "Update an existing blogger password",
            summary = "Update an existing blogger password")
    @Tag(name = "Bloggers")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlogger.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception400.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> updateBloggerPassword(
            @RequestBody @Valid UpdateBloggerPasswordDTO updateBloggerPasswordDTORequest, final Principal principal) {
        final String bloggerId = principal.getName();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(
                        HttpStatus.OK,
                        bloggerService.updateBloggerPassword(updateBloggerPasswordDTORequest, bloggerId)),
                HttpStatus.OK);
    }

    @DeleteMapping("/blogger")
    @Operation(operationId = "deleteBlogger", description = "Delete a blogger", summary = "Delete a blogger")
    @Tag(name = "Bloggers")
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
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlogger.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> deleteBlogger(final Principal principal) {
        final String bloggerId = principal.getName();
        final Blogger deletedBlogger = this.bloggerService.deleteBlogger(bloggerId);
        return new ResponseEntity<>(new GlobalResponseEntity<>(HttpStatus.GONE, deletedBlogger), HttpStatus.GONE);
    }
}
