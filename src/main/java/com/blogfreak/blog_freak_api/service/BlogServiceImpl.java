package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dao.BloggerDAOImpl;
import com.blogfreak.blog_freak_api.dao.BlogsDAOImpl;
import com.blogfreak.blog_freak_api.dao.CategoryDAO;
import com.blogfreak.blog_freak_api.dto.CreateBlogDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBlogDTO;
import com.blogfreak.blog_freak_api.entity.Blog;
import com.blogfreak.blog_freak_api.entity.Blogger;
import com.blogfreak.blog_freak_api.entity.Category;
import com.blogfreak.blog_freak_api.exception.InvalidPatchBlog;
import com.blogfreak.blog_freak_api.util.Constant;
import com.blogfreak.blog_freak_api.util.StringUtility;
import jakarta.transaction.Transactional;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogsDAOImpl blogsDAOImpl;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private BloggerDAOImpl bloggerDAO;

    public BlogServiceImpl(BlogsDAOImpl blogsDAOImpl, BloggerDAOImpl bloggerDAO, CategoryDAO categoryDAO) {
        this.blogsDAOImpl = blogsDAOImpl;
        this.bloggerDAO = bloggerDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Blog> getAllBlogs(String categoryIdsAsCommaSeparated) {
        if (categoryIdsAsCommaSeparated.equalsIgnoreCase("")) return blogsDAOImpl.getAllBlogs();
        final String[] categoryIdsList = categoryIdsAsCommaSeparated.split(Constant.COMMA);
        Set<String> categoryIdsSet = new HashSet<>();
        for (String categoryId : categoryIdsList) categoryIdsSet.add(categoryId);
        return blogsDAOImpl.getListOfAllBlogsInListOfCategories(categoryIdsSet);
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
        List<Category> taggedCategoriesList = new ArrayList<>();
        for (String categoryId : createBlogDTOlogDTO.getCategoryIdList()) {
            taggedCategoriesList.add(this.categoryDAO.getCategoryById(categoryId));
        }
        toBePersistedBlog.setListOfCategories(taggedCategoriesList);
        Date currentDT = new Date();
        toBePersistedBlog.setCreatedAt(currentDT);
        toBePersistedBlog.setUpdatedAt(currentDT);
        toBePersistedBlog.setVersion(Integer.valueOf(1));
        return blogsDAOImpl.createBlog(toBePersistedBlog);
    }

    @Transactional
    @Override
    public Blog deleteBlogByblogId(String blogId, final String bloggerId) {
        return this.blogsDAOImpl.deleteBlogByblogId(blogId, bloggerId);
    }

    @Transactional
    @Override
    public Blog updateBlogByblogId(final String blogId, final UpdateBlogDTO updateBlogDTO, final String bloggerId) {
        final String newBlogTitle = updateBlogDTO.getTitle();
        final String newBlogContent = updateBlogDTO.getContent();
        final String newBlogUrlAttachment = updateBlogDTO.getUrlAttachment();
        final List<String> categoryIdList = updateBlogDTO.getCategoryIdList();
        if (StringUtils.isEmpty(newBlogTitle)
                && StringUtils.isEmpty(newBlogContent)
                && StringUtils.isEmpty(newBlogUrlAttachment)
                && categoryIdList == null) {
            throw new InvalidPatchBlog("One of the allowed properties [title/content/urlAttachment] is mandatory");
        }
        if (categoryIdList.isEmpty())
            throw new InvalidPatchBlog("categoryIdList should either be not present or be non-empty");
        Blog toBeUpdatedBlog = getBlogById(blogId);
        if (!StringUtils.isEmpty(newBlogTitle)) toBeUpdatedBlog.setTitle(newBlogTitle);
        if (!StringUtils.isEmpty(newBlogContent)) toBeUpdatedBlog.setContent(newBlogContent);
        if (!StringUtils.isEmpty(newBlogUrlAttachment)) toBeUpdatedBlog.setUrlAttachment(newBlogUrlAttachment);
        List<Category> taggedCategoriesList = new ArrayList<>();
        for (String categoryId : categoryIdList) {
            taggedCategoriesList.add(this.categoryDAO.getCategoryById(categoryId));
        }
        toBeUpdatedBlog.setListOfCategories(taggedCategoriesList);
        toBeUpdatedBlog.setVersion(
                toBeUpdatedBlog.getVersion() == null ? Integer.valueOf(1) : toBeUpdatedBlog.getVersion() + 1);
        toBeUpdatedBlog.setUpdatedAt(new Date());
        return this.blogsDAOImpl.updateBlogByblogId(toBeUpdatedBlog, bloggerId);
    }
}
