package com.blogfreak.blog_freak_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateCategoryDTO {
    @NotNull(message = "Missing mandatory attribute : name")
    @Size(min = 1, message = "name must be not empty and not null")
    private String name;

    public CreateCategoryDTO() {}

    public CreateCategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
