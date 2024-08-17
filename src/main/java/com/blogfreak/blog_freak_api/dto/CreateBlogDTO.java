package com.blogfreak.blog_freak_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
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

    @NotNull
    @NotEmpty(message = "Blog must be tagged to at-least one of the available categories through categoryId")
    private List<@NotNull @Size(min = 30, max = 36, message = "Category Id should be 30 letters") String>
            categoryIdList;
}
