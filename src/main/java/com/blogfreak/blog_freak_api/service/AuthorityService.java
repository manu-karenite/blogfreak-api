package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.entity.Authority;
import java.util.List;

public interface AuthorityService {
    public List<Authority> getListOfAuthorityForBlogger(String bloggerId);

    public Authority createAuthority(Authority authority);
}
