package com.blogfreak.blog_freak_api.oas;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI renderOpenApi() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.info(new OASInfo().renderOASInfo());
        openAPI.setTags(new OASTags().getOASTags());
        openAPI.setComponents(new OASComponents().getSecurityJWTComponent());
        openAPI.setSecurity(new OASSecurity().getSecurityRequirement());
        return openAPI;
    }
}
