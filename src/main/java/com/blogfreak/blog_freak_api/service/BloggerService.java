package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dto.CreateBloggerDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBloggerDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBloggerPasswordDTO;
import com.blogfreak.blog_freak_api.entity.Blogger;
import java.util.List;

public interface BloggerService {
    public List<Blogger> getAllBloggers();

    public Blogger getBloggerById(String bloggerId);

    public Blogger createBlogger(CreateBloggerDTO createBloggerDTORequest);

    public Blogger updateBlogger(UpdateBloggerDTO updateBloggerDTORequest, String bloggerId);

    public Blogger updateBloggerPassword(UpdateBloggerPasswordDTO updateBloggerPasswordDTO, String bloggerId);
}
