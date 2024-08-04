package com.blogfreak.blog_freak_api.oas.schema.success;

import com.blogfreak.blog_freak_api.entity.Blog;
import lombok.Data;

@Data
public class SuccessBlog extends BaseSuccess {
    private Blog blog;
}
