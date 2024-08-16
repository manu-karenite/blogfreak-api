package com.blogfreak.blog_freak_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateBlogDTO {
    @NotNull(message = "title is missing")
    @Size(min = 4, message = "title must contain 4 or more letters")
    private String title;

    @NotNull(message = "content is missing")
    @Size(min = 10, message = "content must contain 10 or more letters")
    private String content;

    private String urlAttachment;
}
