package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.entity.Blog;
import com.blogfreak.blog_freak_api.entity.Blogger;
import java.util.List;

public interface BlogsDAO {
    public List<Blog> getAllBlogs();

    public Blog getBlogById(String blogId);

    public Blog createBlog(Blog blog);

    public Blog deleteBlogByblogId(final String blogId, final String bloggerId);

    public Blog updateBlogByblogId(final Blog tobeUpdatedBlog, final String bloggerId);

    public List<Blog> getListOfBlogsForBlogger(final String bloggerId, Blogger blogger);
}
