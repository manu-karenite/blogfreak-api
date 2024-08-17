package com.blogfreak.blog_freak_api.security;

import com.blogfreak.blog_freak_api.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BlogFreakApplicationAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public BlogFreakApplicationAuthenticationProvider(
            UserDetailsServiceImpl userDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // This will be invoked from login endpoint - based on the DTO Received
        final String bloggerEmail = authentication.getName();
        final String bloggerPassword = authentication.getCredentials().toString();

        UserDetails toBeAuthenticatedUser = this.userDetailsServiceImpl.loadUserByUsername(bloggerEmail);
        if (!this.passwordEncoder.matches(bloggerPassword, toBeAuthenticatedUser.getPassword())) {
            throw new UnauthorizedException("Wrong password");
        }
        return new UsernamePasswordAuthenticationToken(
                toBeAuthenticatedUser.getUsername(), bloggerPassword, toBeAuthenticatedUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
