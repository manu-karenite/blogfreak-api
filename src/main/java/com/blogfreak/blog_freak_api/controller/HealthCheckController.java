package com.blogfreak.blog_freak_api.controller;

import com.blogfreak.blog_freak_api.exception.RateLimitExceeded;
import com.blogfreak.blog_freak_api.oas.schema.error.Exception429;
import com.blogfreak.blog_freak_api.oas.schema.error.Exception500;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessHealthCheck;
import com.blogfreak.blog_freak_api.service.HealthCheckService;
import com.google.common.util.concurrent.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @Autowired
    RateLimiter getRateLimiter;

    private final String getHealthDescription =
            "**Operation** : Check if blog-freak application service is up and running. Successful response returns application version and db version running in production.\n\n";

    @Autowired
    private HealthCheckService healthCheckServiceImpl;

    public HealthCheckController(HealthCheckService healthCheckServiceImpl, RateLimiter getRateLimiter) {
        this.healthCheckServiceImpl = healthCheckServiceImpl;
        this.getRateLimiter = getRateLimiter;
    }

    @GetMapping("/healthcheck")
    @Operation(
            operationId = "getServiceVersion",
            description = getHealthDescription,
            summary = "Check if blog-freak application service is up and running")
    @ApiResponse(
            responseCode = "200",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuccessHealthCheck.class)))
    @ApiResponse(
            responseCode = "429",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception429.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @Tag(name = "Health Check")
    @SecurityRequirements(value = {})
    public ResponseEntity<GlobalResponseEntity> getServiceVersion() {
        if (!getRateLimiter.tryAcquire()) throw new RateLimitExceeded();
        return new ResponseEntity<>(
                new GlobalResponseEntity(HttpStatus.OK, healthCheckServiceImpl.getServiceVersion()), HttpStatus.OK);
    }
}
