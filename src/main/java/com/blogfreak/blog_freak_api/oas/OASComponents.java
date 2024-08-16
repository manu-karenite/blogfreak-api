package com.blogfreak.blog_freak_api.oas;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class OASComponents {
    public Components getSecurityJWTComponent() {
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.setName("Blog Freak JWT Authentication");
        securityScheme.setScheme("bearer");
        securityScheme.setType(SecurityScheme.Type.HTTP);
        securityScheme.setBearerFormat("JWT");
        Components components = new Components();
        components.addSecuritySchemes("Blog Freak JWT Authentication", securityScheme);
        return components;
    }
}
