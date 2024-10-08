package com.blogfreak.blog_freak_api.controller;

import com.blogfreak.blog_freak_api.dto.UpdateBloggerDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBloggerPasswordDTO;
import com.blogfreak.blog_freak_api.entity.Blogger;
import com.blogfreak.blog_freak_api.exception.RateLimitExceeded;
import com.blogfreak.blog_freak_api.oas.schema.error.*;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessBlogger;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessListOfAllBloggers;
import com.blogfreak.blog_freak_api.service.BloggerService;
import com.blogfreak.blog_freak_api.util.Constant;
import com.google.common.util.concurrent.RateLimiter;
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
    private final String getAllBloggersDescription = "**Operation** : Get all bloggers existing in the application\n\n";
    private final String getABloggerByBloggerIdDescription =
            "**Operation** : Get a unique blogger existing in the application identified by bloggerId\n\n";
    private final String updateBloggerDescription =
            "**Operation** : Update a blogger's details belonging to the current logged-in blogger\n\n";
    private final String updateBloggerPasswordDescription =
            "**Operation** : Update a blogger's password belonging to the current logged-in blogger\n\n";
    private final String deleteBloggerPasswordDescription =
            "**Operation** : Delete a blogger's profile from blog-freak application belonging to the current logged-in blogger\n\n";
    private final String getBloggerProfileDescription =
            "**Operation** : Get the blogger's profile from blog-freak application belonging to the current logged-in blogger\n\n";

    @Autowired
    BloggerService bloggerService;

    @Autowired
    RateLimiter getRateLimiter;

    @Autowired
    RateLimiter postRateLimiter;

    @Autowired
    RateLimiter patchRateLimiter;

    @Autowired
    RateLimiter deleteRateLimiter;

    public BloggerController(
            BloggerService bloggerService,
            RateLimiter getRateLimiter,
            RateLimiter postRateLimiter,
            RateLimiter patchRateLimiter,
            RateLimiter deleteRateLimiter) {
        this.bloggerService = bloggerService;
        this.getRateLimiter = getRateLimiter;
        this.postRateLimiter = postRateLimiter;
        this.patchRateLimiter = patchRateLimiter;
        this.deleteRateLimiter = deleteRateLimiter;
    }

    @GetMapping("/bloggers")
    @Operation(
            operationId = "getAllBloggers",
            description = getAllBloggersDescription + Constant.OAS_READ_AUTH,
            summary = "Get all bloggers existing in the application")
    @ApiResponse(
            responseCode = "200",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuccessListOfAllBloggers.class)))
    @ApiResponse(
            responseCode = "401",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception401.class)))
    @ApiResponse(
            responseCode = "403",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception403.class)))
    @ApiResponse(
            responseCode = "429",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception429.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @Tag(name = "Bloggers")
    public ResponseEntity<GlobalResponseEntity> getAllBloggers() {
        if (!getRateLimiter.tryAcquire()) throw new RateLimitExceeded();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(HttpStatus.OK, bloggerService.getAllBloggers()), HttpStatus.OK);
    }

    @GetMapping("/bloggers/{bloggerId}")
    @Operation(
            operationId = "getBloggerById",
            description = getABloggerByBloggerIdDescription + Constant.OAS_READ_AUTH,
            summary = "Get a unique blogger existing in the application identified by bloggerId")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlogger.class)))
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
            responseCode = "429",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception429.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @Tag(name = "Bloggers")
    public ResponseEntity<GlobalResponseEntity> getBloggerById(@PathVariable @NotNull @Size(min = 1) String bloggerId) {
        if (!getRateLimiter.tryAcquire()) throw new RateLimitExceeded();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(HttpStatus.OK, bloggerService.getBloggerById(bloggerId)), HttpStatus.OK);
    }

    @PatchMapping("/blogger")
    @Operation(
            operationId = "updateBlogger",
            description = updateBloggerDescription + Constant.OAS_MANAGE_AUTH,
            summary = "Update a blogger's details belonging to the current logged-in blogger")
    @Tag(name = "Bloggers")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlogger.class)))
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
            responseCode = "429",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception429.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> updateBlogger(
            @RequestBody @Valid UpdateBloggerDTO updateBloggerDTORequest, final Principal principal) {
        if (!patchRateLimiter.tryAcquire()) throw new RateLimitExceeded();
        final String bloggerId = principal.getName();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(
                        HttpStatus.OK, bloggerService.updateBlogger(updateBloggerDTORequest, bloggerId)),
                HttpStatus.OK);
    }

    @PatchMapping("/blogger/password")
    @Operation(
            operationId = "updateBloggerPassword",
            description = updateBloggerPasswordDescription + Constant.OAS_MANAGE_AUTH,
            summary = "Update a blogger's password belonging to the current logged-in blogger")
    @Tag(name = "Bloggers")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlogger.class)))
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
            responseCode = "429",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception429.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> updateBloggerPassword(
            @RequestBody @Valid UpdateBloggerPasswordDTO updateBloggerPasswordDTORequest, final Principal principal) {
        if (!patchRateLimiter.tryAcquire()) throw new RateLimitExceeded();
        final String bloggerId = principal.getName();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(
                        HttpStatus.OK,
                        bloggerService.updateBloggerPassword(updateBloggerPasswordDTORequest, bloggerId)),
                HttpStatus.OK);
    }

    @DeleteMapping("/blogger")
    @Operation(
            operationId = "deleteBlogger",
            description = deleteBloggerPasswordDescription + Constant.AUTHORITY_DELETE,
            summary =
                    "Delete a blogger's profile from blog-freak application belonging to the current logged-in blogger")
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
            responseCode = "429",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception429.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    public ResponseEntity<GlobalResponseEntity> deleteBlogger(final Principal principal) {
        if (!deleteRateLimiter.tryAcquire()) throw new RateLimitExceeded();
        final String bloggerId = principal.getName();
        final Blogger deletedBlogger = this.bloggerService.deleteBlogger(bloggerId);
        return new ResponseEntity<>(new GlobalResponseEntity<>(HttpStatus.GONE, deletedBlogger), HttpStatus.GONE);
    }

    @GetMapping("/blogger/profile")
    @Operation(
            operationId = "getLoggedInBloggerProfile",
            description = getBloggerProfileDescription + Constant.OAS_READ_AUTH,
            summary = "Get profile details of the current logged-in blogger")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlogger.class)))
    @ApiResponse(
            responseCode = "401",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception401.class)))
    @ApiResponse(
            responseCode = "403",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception403.class)))
    @ApiResponse(
            responseCode = "429",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception429.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @Tag(name = "Bloggers")
    public ResponseEntity<GlobalResponseEntity> getBloggerProfile(final Principal principal) {
        if (!getRateLimiter.tryAcquire()) throw new RateLimitExceeded();
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(HttpStatus.OK, bloggerService.getBloggerById(principal.getName())),
                HttpStatus.OK);
    }
}
