package com.blogfreak.blog_freak_api.exception;

public class DuplicateBlogger extends RuntimeException {
    public DuplicateBlogger() {}

    public DuplicateBlogger(String message) {
        super(message);
    }

    public DuplicateBlogger(String message, Throwable cause) {
        super(message, cause);
    }
}
