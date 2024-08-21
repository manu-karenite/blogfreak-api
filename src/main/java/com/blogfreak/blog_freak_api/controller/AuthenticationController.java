package com.blogfreak.blog_freak_api.controller;

import com.blogfreak.blog_freak_api.dto.CreateBloggerDTO;
import com.blogfreak.blog_freak_api.dto.LoginDTO;
import com.blogfreak.blog_freak_api.entity.Blogger;
import com.blogfreak.blog_freak_api.oas.schema.error.Exception400;
import com.blogfreak.blog_freak_api.oas.schema.error.Exception401;
import com.blogfreak.blog_freak_api.oas.schema.error.Exception404;
import com.blogfreak.blog_freak_api.oas.schema.error.Exception500;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessBlogger;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessLogin;
import com.blogfreak.blog_freak_api.service.AuthenticationService;
import com.blogfreak.blog_freak_api.service.AuthenticationServiceImpl;
import com.blogfreak.blog_freak_api.service.BloggerService;
import com.blogfreak.blog_freak_api.util.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class AuthenticationController {
    private final String loginDescription =
            "**Operation** : Login to the blog-freak application using emailAddress and password\n\n";
    private final String registerDescription =
            "**Operation** : Register in the blog-freak application as a new user(blogger). On successful registration, each new blogger is authorised with 4 authorities [READ/WRITE/DELETE/MANAGE]\n\n";

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    BloggerService bloggerService;

    public AuthenticationController(
            AuthenticationServiceImpl authenticationServiceImpl,
            AuthenticationManager authenticationManager,
            BloggerService bloggerService) {
        this.authenticationService = authenticationServiceImpl;
        this.authenticationManager = authenticationManager;
        this.bloggerService = bloggerService;
    }

    @PostMapping("/login")
    @Operation(
            operationId = "login",
            description = loginDescription,
            summary = "Login to the blog-freak application using emailAddress and password")
    @Tag(name = "Authentication")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessLogin.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception400.class)))
    @ApiResponse(
            responseCode = "401",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception401.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception404.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @SecurityRequirements(value = {})
    public ResponseEntity<GlobalResponseEntity> loginUser(@Validated @RequestBody LoginDTO loginDTO) {
        Authentication authentication =
                UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getEmailId(), loginDTO.getPassword());
        authentication = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // User can be deemed as login now
        if (authentication != null) {
            final String successfulLoginToken = this.authenticationService.loginUser();
            authentication = UsernamePasswordAuthenticationToken.unauthenticated(
                    authentication.getPrincipal(), loginDTO.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final MultiValueMap<String, String> headers = new HttpHeaders();
            headers.set(Constant.AUTHORIZATION, String.format("%s %s", Constant.BEARER, successfulLoginToken));
            return new ResponseEntity<>(
                    new GlobalResponseEntity(HttpStatus.OK, successfulLoginToken), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new GlobalResponseEntity(HttpStatus.UNAUTHORIZED, "401"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    @Operation(
            operationId = "createBlogger",
            description = registerDescription,
            summary = "Register a new user - Each user in blogfreak application is a blogger!")
    @Tag(name = "Authentication")
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessBlogger.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception400.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @SecurityRequirements(value = {})
    public ResponseEntity<GlobalResponseEntity> createBlogger(@Valid @RequestBody CreateBloggerDTO createBloggerDTO) {
        Blogger blogger = bloggerService.createBlogger(createBloggerDTO);
        Blogger refreshedBlogger = bloggerService.getBloggerById(blogger.getId());
        return new ResponseEntity<>(
                new GlobalResponseEntity<>(HttpStatus.CREATED, refreshedBlogger), HttpStatus.CREATED);
    }
}
