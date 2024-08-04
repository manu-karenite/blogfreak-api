package com.blogfreak.blog_freak_api.exception;

public class BlogNotFound extends RuntimeException {
    public BlogNotFound() {}

    public BlogNotFound(String message) {
        super(message);
    }

    public BlogNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
