package com.blogfreak.blog_freak_api.controller;

import com.blogfreak.blog_freak_api.oas.schema.error.Exception500;
import com.blogfreak.blog_freak_api.oas.schema.success.SuccessHealthCheck;
import com.blogfreak.blog_freak_api.service.HealthCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckServiceImpl;

    public HealthCheckController(HealthCheckService healthCheckServiceImpl) {
        this.healthCheckServiceImpl = healthCheckServiceImpl;
    }

    @GetMapping("/healthcheck")
    @Operation(operationId = "getServiceVersion", description = "Get service version", summary = "Get service version")
    @ApiResponse(
            responseCode = "200",
            content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuccessHealthCheck.class)))
    @ApiResponse(
            responseCode = "500",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Exception500.class)))
    @Tag(name = "Health Check")
    public ResponseEntity<GlobalResponseEntity> getServiceVersion() {
        return new ResponseEntity<>(
                new GlobalResponseEntity(HttpStatus.OK, healthCheckServiceImpl.getServiceVersion()), HttpStatus.OK);
    }
}
