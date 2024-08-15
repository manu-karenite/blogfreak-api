package com.blogfreak.blog_freak_api.service;

import com.blogfreak.blog_freak_api.util.Constant;
import com.blogfreak.blog_freak_api.util.JWTUtils;
import io.jsonwebtoken.Jwts;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public String loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getName();
        List<String> grantedAuthorityList = new ArrayList<>();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            grantedAuthorityList.add(authority.getAuthority());
        }
        final String commaSeparatedAuthoritiesStr = String.join(Constant.COMMA, grantedAuthorityList);
        SecretKey secretKey = JWTUtils.getSecretKey();
        final String jwt = Jwts.builder()
                .issuer(JWTUtils.getJwtIssuer())
                .subject(JWTUtils.getJwtSubject())
                .claim(Constant.EMAILID, username)
                .claim(Constant.AUTHORITIES, commaSeparatedAuthoritiesStr)
                .issuedAt(JWTUtils.getJwtIssueDate())
                .expiration(JWTUtils.getJwtExpiryDate())
                .signWith(secretKey)
                .compact();
        return jwt;
    }
}
