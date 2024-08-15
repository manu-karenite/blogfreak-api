package com.blogfreak.blog_freak_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {
    @NotNull(message = "emailId is missing")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String emailId;

    @NotNull(message = "password is missing")
    @Size(min = 6, message = "password must contain 6 or more letters")
    private String password;
}
