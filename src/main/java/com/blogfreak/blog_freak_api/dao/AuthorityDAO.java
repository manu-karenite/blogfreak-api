package com.blogfreak.blog_freak_api.dao;

import com.blogfreak.blog_freak_api.entity.Authority;
import java.util.List;

public interface AuthorityDAO {
    public List<Authority> getListOfAuthorityForBlogger(String bloggerId);

    public Authority createAuthority(Authority authority);
}
