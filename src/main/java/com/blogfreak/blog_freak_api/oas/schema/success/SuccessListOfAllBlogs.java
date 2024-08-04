package com.blogfreak.blog_freak_api.oas.schema.success;

import com.blogfreak.blog_freak_api.entity.Blog;
import java.util.List;
import lombok.Data;

@Data
public class SuccessListOfAllBlogs extends BaseSuccess {
    private List<Blog> listOfAllBlogs;
}
