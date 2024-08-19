package com.blogfreak.blog_freak_api.oas.schema.success;

import com.blogfreak.blog_freak_api.dto.GetLikesForBlogDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessGetLikesForBlog extends BaseSuccess {
    GetLikesForBlogDTO data;
}
