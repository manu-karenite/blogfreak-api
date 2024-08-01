package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dao.BloggerDAO;
import com.blogfreak.blog_freak_api.dto.CreateBloggerDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBloggerDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBloggerPasswordDTO;
import com.blogfreak.blog_freak_api.entity.Blogger;
import com.blogfreak.blog_freak_api.exception.InvalidPatchBlogger;
import com.blogfreak.blog_freak_api.util.EnumValidation;
import jakarta.transaction.Transactional;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloggerServiceImpl implements BloggerService {
    @Autowired
    BloggerDAO bloggerDAO;

    public BloggerServiceImpl(BloggerDAO bloggerDAO) {
        this.bloggerDAO = bloggerDAO;
    }

    @Override
    public List<Blogger> getAllBloggers() {
        return bloggerDAO.getAllBloggers();
    }

    @Override
    public Blogger getBloggerById(String bloggerId) {
        return bloggerDAO.getBloggerById(bloggerId);
    }

    @Transactional
    @Override
    public Blogger createBlogger(CreateBloggerDTO createBloggerDTORequest) {
        Blogger blogger = new Blogger();
        blogger.setId(String.valueOf(System.currentTimeMillis()));
        blogger.setFirstName(createBloggerDTORequest.getFirstName());
        // LastName is optional
        if (StringUtils.isEmpty(createBloggerDTORequest.getLastName())) {
            blogger.setLastName(createBloggerDTORequest.getLastName());
        }
        blogger.setEmailId(createBloggerDTORequest.getEmailId());
        blogger.setGender(createBloggerDTORequest.getGender());
        blogger.setPassword(createBloggerDTORequest.getPassword());
        return bloggerDAO.createBlogger(blogger);
    }

    @Transactional
    @Override
    public Blogger updateBlogger(UpdateBloggerDTO updateBloggerDTORequest, String bloggerId) {
        String firstName = updateBloggerDTORequest.getFirstName();
        String lastName = updateBloggerDTORequest.getLastName();
        String gender = updateBloggerDTORequest.getGender();
        if (StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName) && StringUtils.isEmpty(gender)) {
            throw new InvalidPatchBlogger("One of the allowed properties [firstName/lastName/gender] is mandatory");
        }
        if (gender != null) EnumValidation.validateGender(gender);
        Blogger blogger = bloggerDAO.getBloggerById(bloggerId);
        if (!StringUtils.isEmpty(firstName)) {
            blogger.setFirstName(firstName);
        }
        if (!StringUtils.isEmpty(lastName)) {
            blogger.setLastName(lastName);
        }
        if (!StringUtils.isEmpty(gender)) {
            blogger.setGender(gender);
        }
        return bloggerDAO.updateBlogger(blogger, bloggerId);
    }

    @Override
    @Transactional
    public Blogger updateBloggerPassword(UpdateBloggerPasswordDTO updateBloggerPasswordDTO, String bloggerId) {
        String password = updateBloggerPasswordDTO.getPassword();
        return bloggerDAO.updateBloggerPassword(password, bloggerId);
    }
}
