package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dao.AuthorityDAO;
import com.blogfreak.blog_freak_api.dao.BloggerDAO;
import com.blogfreak.blog_freak_api.dto.CreateBloggerDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBloggerDTO;
import com.blogfreak.blog_freak_api.dto.UpdateBloggerPasswordDTO;
import com.blogfreak.blog_freak_api.entity.Authority;
import com.blogfreak.blog_freak_api.entity.Blogger;
import com.blogfreak.blog_freak_api.exception.DuplicateBlogger;
import com.blogfreak.blog_freak_api.exception.InvalidPatchBlogger;
import com.blogfreak.blog_freak_api.util.Constant;
import com.blogfreak.blog_freak_api.util.EnumValidation;
import com.blogfreak.blog_freak_api.util.StringUtility;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BloggerServiceImpl implements BloggerService {
    @Autowired
    BloggerDAO bloggerDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityDAO authorityDAO;

    public BloggerServiceImpl(BloggerDAO bloggerDAO, AuthorityDAO authorityDAO, PasswordEncoder passwordEncoder) {
        this.bloggerDAO = bloggerDAO;
        this.authorityDAO = authorityDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Blogger> getAllBloggers() {
        return bloggerDAO.getAllBloggers();
    }

    @Override
    public Blogger getBloggerById(String bloggerId) {
        Blogger blogger = bloggerDAO.getBloggerById(bloggerId);
        return blogger;
    }

    public Blogger getBloggerByEmail(String bloggerEmail) {
        return this.bloggerDAO.getBloggerByEmail(bloggerEmail);
    }

    @Transactional
    private Blogger createBloggerHelper(CreateBloggerDTO createBloggerDTORequest) {
        Blogger bloggerWithSameEmailAddress = this.bloggerDAO.getBloggerByEmail(createBloggerDTORequest.getEmailId());
        if (bloggerWithSameEmailAddress != null) {
            throw new DuplicateBlogger(
                    String.format("Blogger with emailId %s already exists", createBloggerDTORequest.getEmailId()));
        }
        Blogger blogger = new Blogger();
        blogger.setId(StringUtility.generateIdForEntity());
        blogger.setFirstName(createBloggerDTORequest.getFirstName());
        // LastName is optional
        if (StringUtils.isEmpty(createBloggerDTORequest.getLastName())) {
            blogger.setLastName("");
        } else {
            blogger.setLastName(createBloggerDTORequest.getLastName());
        }
        blogger.setEmailId(createBloggerDTORequest.getEmailId());
        blogger.setGender(createBloggerDTORequest.getGender());
        blogger.setPassword(passwordEncoder.encode(createBloggerDTORequest.getPassword()));
        Date currentDT = new Date();
        blogger.setRegisteredAt(currentDT);
        blogger.setUpdatedAt(currentDT);
        blogger.setVersion(Integer.valueOf(1));
        return bloggerDAO.createBlogger(blogger);
    }

    @Transactional
    private Blogger createAuthoritiesForBloggerHelper(Blogger blogger) {
        Authority authority = new Authority(StringUtility.generateIdForEntity(), blogger, Constant.AUTHORITY_READ);
        this.authorityDAO.createAuthority(authority);
        authority = new Authority(StringUtility.generateIdForEntity(), blogger, Constant.AUTHORITY_WRITE);
        this.authorityDAO.createAuthority(authority);
        authority = new Authority(StringUtility.generateIdForEntity(), blogger, Constant.AUTHORITY_DELETE);
        this.authorityDAO.createAuthority(authority);
        authority = new Authority(StringUtility.generateIdForEntity(), blogger, Constant.AUTHORITY_MANAGE);
        this.authorityDAO.createAuthority(authority);
        return blogger;
    }

    @Transactional
    @Override
    public Blogger createBlogger(CreateBloggerDTO createBloggerDTORequest) {
        Blogger blogger = createBloggerHelper(createBloggerDTORequest);
        blogger = createAuthoritiesForBloggerHelper(blogger);
        return blogger;
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
        blogger.setVersion(blogger.getVersion() == null ? Integer.valueOf(1) : blogger.getVersion() + 1);
        blogger.setUpdatedAt(new Date());
        return bloggerDAO.updateBlogger(blogger, bloggerId);
    }

    @Override
    @Transactional
    public Blogger updateBloggerPassword(UpdateBloggerPasswordDTO updateBloggerPasswordDTO, String bloggerId) {
        Blogger toBeUpdatedBlogger = this.bloggerDAO.getBloggerById(bloggerId);
        String password = passwordEncoder.encode(updateBloggerPasswordDTO.getPassword());
        toBeUpdatedBlogger.setPassword(password);
        toBeUpdatedBlogger.setVersion(
                toBeUpdatedBlogger.getVersion() == null ? Integer.valueOf(1) : toBeUpdatedBlogger.getVersion() + 1);
        toBeUpdatedBlogger.setUpdatedAt(new Date());
        return bloggerDAO.updateBloggerPassword(toBeUpdatedBlogger);
    }

    @Transactional
    @Override
    public Blogger deleteBlogger(final String bloggerId) {
        return this.bloggerDAO.deleteBlogger(bloggerId);
    }
}
