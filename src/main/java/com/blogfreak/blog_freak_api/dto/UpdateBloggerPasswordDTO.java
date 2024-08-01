package com.blogfreak.blog_freak_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateBloggerPasswordDTO {
    @NotNull(message = "password is missing")
    @Size(min = 6, message = "password must contain 6 or more letters")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UpdateBloggerPasswordDTO(String password) {
        this.password = password;
    }

    public UpdateBloggerPasswordDTO() {}
}
