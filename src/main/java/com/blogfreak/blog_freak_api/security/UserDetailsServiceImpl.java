package com.blogfreak.blog_freak_api.security;

import com.blogfreak.blog_freak_api.dao.BloggerDAO;
import com.blogfreak.blog_freak_api.dao.BloggerDAOImpl;
import com.blogfreak.blog_freak_api.entity.Authority;
import com.blogfreak.blog_freak_api.entity.Blogger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    BloggerDAO bloggerDAO;

    public UserDetailsServiceImpl(BloggerDAOImpl bloggerDAO) {
        this.bloggerDAO = bloggerDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String bloggerEmail) throws UsernameNotFoundException {
        Blogger blogger = this.bloggerDAO.getBloggerByEmail(bloggerEmail);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        List<Authority> authorityList = blogger.getListOfAuthorities();
        for (Authority a : authorityList) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(a.getAuthority()));
        }
        return new User(blogger.getEmailId(), blogger.getPassword(), grantedAuthorityList);
    }
}
