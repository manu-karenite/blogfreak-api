package com.blogfreak.blog_freak_api.security;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogFreakRateLimiter {

    @Bean
    public RateLimiter getRateLimiter() {
        return RateLimiter.create(500.0 / 60.0);
    }

    @Bean
    public RateLimiter postRateLimiter() {
        return RateLimiter.create(300 / 60.0);
    }

    @Bean
    public RateLimiter patchRateLimiter() {
        return RateLimiter.create(300 / 60.0);
    }

    @Bean
    public RateLimiter deleteRateLimiter() {
        return RateLimiter.create(180 / 60.0);
    }
}
