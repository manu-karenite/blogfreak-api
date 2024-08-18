package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dto.CreateBlogDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBlogDTO;
import com.blogfreak.blog_freak_api.entity.Blog;
import java.util.List;

public interface BlogService {
    public List<Blog> getAllBlogs(String categoryIdsAsCommaSeparated);

    public Blog getBlogById(String blogId);

    public Blog createBlog(CreateBlogDTO createBlogDTO);

    public Blog deleteBlogByblogId(final String blogId, final String bloggerId);

    public Blog updateBlogByblogId(final String blogId, final UpdateBlogDTO updateBlogDTO, final String bloggerId);

    public void likeABlogByBlogId(final String blogId, final String bloggerId);
}
