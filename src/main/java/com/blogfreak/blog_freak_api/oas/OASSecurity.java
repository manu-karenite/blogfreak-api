package com.blogfreak.blog_freak_api.oas;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.ArrayList;
import java.util.List;

public class OASSecurity {
    public List<SecurityRequirement> getSecurityRequirement() {
        List<SecurityRequirement> securityRequirementsList = new ArrayList<>();
        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("Blog Freak JWT Authentication");
        securityRequirementsList.add(securityRequirement);
        return securityRequirementsList;
    }
}
