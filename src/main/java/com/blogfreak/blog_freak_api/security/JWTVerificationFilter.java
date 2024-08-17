package com.blogfreak.blog_freak_api.security;

import com.blogfreak.blog_freak_api.util.Constant;
import com.blogfreak.blog_freak_api.util.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTVerificationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String authorizationHeaderStr = request.getHeader(Constant.AUTHORIZATION);
            if (authorizationHeaderStr == null || authorizationHeaderStr.isEmpty()) {
                throw new BadCredentialsException(
                        String.format("%s not found in request header", Constant.AUTHORIZATION));
            }
            final String[] authorizationHeaderStrSplit = authorizationHeaderStr.split(" ");
            if (authorizationHeaderStrSplit.length != 2
                    || !authorizationHeaderStrSplit[0].equalsIgnoreCase(Constant.BEARER)) {
                throw new BadCredentialsException(String.format(
                        "%s header not in the desired format: [%s: %s <token>]",
                        Constant.AUTHORIZATION, Constant.AUTHORIZATION, Constant.BEARER));
            }
            final String bearerTokenStr = authorizationHeaderStrSplit[1];
            final SecretKey secretKey = JWTUtils.getSecretKey();
            final Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(bearerTokenStr)
                    .getPayload();
            if (claims == null) {
                throw new BadCredentialsException("Malformed JWT. Authentication denied");
            }

            final String userId = (String) claims.get(Constant.ID);
            final String commaSeparatedAuthoritiesStr = (String) claims.get(Constant.AUTHORITIES);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userId, null, AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedAuthoritiesStr));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private boolean getStartsWithServletPathCheck(HttpServletRequest request) {
        final String[] paths = {"GET|/v3/api-docs", "GET|/swagger-ui"};
        final String servletPath = request.getServletPath();
        final String httpMethod = request.getMethod();
        for (String it : paths) {
            if (servletPath.startsWith(it.split("\\|")[1]) && httpMethod.equalsIgnoreCase(it.split("\\|")[0]))
                return true;
        }
        return false;
    }

    private boolean equalsToServletPathCheck(final HttpServletRequest request) {
        final String[] paths = {"GET|/swagger-ui.html", "POST|/login", "POST|/bloggers", "GET|/healthcheck"};
        final String servletPath = request.getServletPath();
        final String httpMethod = request.getMethod();
        for (String it : paths) {
            if (servletPath.equalsIgnoreCase(it.split("\\|")[1]) && httpMethod.equalsIgnoreCase(it.split("\\|")[0]))
                return true;
        }
        return false;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return equalsToServletPathCheck(request) || getStartsWithServletPathCheck(request);
    }
}
