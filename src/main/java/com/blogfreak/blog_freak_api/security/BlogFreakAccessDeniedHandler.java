package com.blogfreak.blog_freak_api.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class BlogFreakAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        final String payload = String.format(
                "{\"timestamp\":\"%s\",\"statusCodeMessage\":\"%s\",\"statusCode\":\"%d\",\"message\":\"%s\"}",
                new Date().toString(),
                HttpStatus.FORBIDDEN,
                HttpStatus.FORBIDDEN.value(),
                accessDeniedException.getMessage());
        response.getWriter().write(payload);
    }
}
