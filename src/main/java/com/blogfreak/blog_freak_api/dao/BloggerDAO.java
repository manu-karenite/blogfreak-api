package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.entity.Blogger;
import java.util.List;

public interface BloggerDAO {
    public List<Blogger> getAllBloggers();

    public Blogger getBloggerById(String bloggerId);

    public Blogger createBlogger(Blogger blogger);

    public Blogger updateBlogger(Blogger blogger, String bloggerId);

    public Blogger updateBloggerPassword(String password, String bloggerId);
}
