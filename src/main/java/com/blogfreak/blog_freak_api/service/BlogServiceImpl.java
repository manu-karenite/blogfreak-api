package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dao.BlogsDAOImpl;
import com.blogfreak.blog_freak_api.entity.Blog;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogsDAOImpl blogsDAOImpl;

    public BlogServiceImpl(BlogsDAOImpl blogsDAOImpl) {
        this.blogsDAOImpl = blogsDAOImpl;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogsDAOImpl.getAllBlogs();
    }

    @Override
    public Blog getBlogById(String blogId) {
        return blogsDAOImpl.getBlogById(blogId);
    }
}
