package com.blogfreak.blog_freak_api.exception;

public class RateLimitExceeded extends RuntimeException {
    public RateLimitExceeded() {}

    public RateLimitExceeded(String message) {
        super(message);
    }

    public RateLimitExceeded(String message, Throwable cause) {
        super(message, cause);
    }
}
