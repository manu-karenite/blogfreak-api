package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dao.BloggerDAOImpl;
import com.blogfreak.blog_freak_api.dao.BlogsDAOImpl;
import com.blogfreak.blog_freak_api.dto.CreateBlogDTO;
import com.blogfreak.blog_freak_api.entity.Blog;
import com.blogfreak.blog_freak_api.entity.Blogger;
import com.blogfreak.blog_freak_api.util.StringUtility;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogsDAOImpl blogsDAOImpl;

    @Autowired
    private BloggerDAOImpl bloggerDAO;

    public BlogServiceImpl(BlogsDAOImpl blogsDAOImpl, BloggerDAOImpl bloggerDAO) {
        this.blogsDAOImpl = blogsDAOImpl;
        this.bloggerDAO = bloggerDAO;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogsDAOImpl.getAllBlogs();
    }

    @Override
    public Blog getBlogById(String blogId) {
        return blogsDAOImpl.getBlogById(blogId);
    }

    @Transactional
    @Override
    public Blog createBlog(CreateBlogDTO createBlogDTOlogDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Blogger blogger = this.bloggerDAO.getBloggerById(authentication.getName());
        Blog toBePersistedBlog = new Blog();
        toBePersistedBlog.setId(StringUtility.generateIdForEntity());
        toBePersistedBlog.setTitle(createBlogDTOlogDTO.getTitle());
        toBePersistedBlog.setContent(createBlogDTOlogDTO.getContent());
        if (createBlogDTOlogDTO.getUrlAttachment() == null
                || createBlogDTOlogDTO.getUrlAttachment().isEmpty()) {
            toBePersistedBlog.setUrlAttachment("No Attachment");
        } else {
            toBePersistedBlog.setUrlAttachment(createBlogDTOlogDTO.getUrlAttachment());
        }
        final Date currentDate = new Date();
        toBePersistedBlog.setCreatedAt(currentDate);
        toBePersistedBlog.setUpdatedAt(currentDate);
        toBePersistedBlog.setBlogger(blogger);
        return blogsDAOImpl.createBlog(toBePersistedBlog);
    }
}
