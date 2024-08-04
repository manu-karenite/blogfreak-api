package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.entity.Blog;
import java.util.List;

public interface BlogService {
    public List<Blog> getAllBlogs();

    public Blog getBlogById(String blogId);
}
