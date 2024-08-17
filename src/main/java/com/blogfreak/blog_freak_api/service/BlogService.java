package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dto.CreateBlogDTO;
import com.blogfreak.blog_freak_api.entity.Blog;
import java.util.List;

public interface BlogService {
    public List<Blog> getAllBlogs(String categoryIdsAsCommaSeparated);

    public Blog getBlogById(String blogId);

    public Blog createBlog(CreateBlogDTO createBlogDTO);
}
