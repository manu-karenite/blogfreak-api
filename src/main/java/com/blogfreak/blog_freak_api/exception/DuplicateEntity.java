package com.blogfreak.blog_freak_api.exception;

public class DuplicateEntity extends RuntimeException {
    public DuplicateEntity() {}

    public DuplicateEntity(String message) {
        super(message);
    }

    public DuplicateEntity(String message, Throwable cause) {
        super(message, cause);
    }
}
