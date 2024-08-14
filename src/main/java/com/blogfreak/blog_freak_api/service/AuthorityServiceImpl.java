package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.dao.AuthorityDAO;
import com.blogfreak.blog_freak_api.entity.Authority;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityDAO authorityDAO;

    public AuthorityServiceImpl(AuthorityDAO authorityDAO) {
        this.authorityDAO = authorityDAO;
    }

    @Override
    public List<Authority> getListOfAuthorityForBlogger(String bloggerId) {
        return this.getListOfAuthorityForBlogger(bloggerId);
    }

    @Transactional
    @Override
    public Authority createAuthority(Authority authority) {
        return this.authorityDAO.createAuthority(authority);
    }
}
