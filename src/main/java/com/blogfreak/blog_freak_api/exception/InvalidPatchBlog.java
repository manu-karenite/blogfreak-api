package com.blogfreak.blog_freak_api.exception;

public class InvalidPatchBlog extends RuntimeException {
    public InvalidPatchBlog() {}

    public InvalidPatchBlog(String message) {
        super(message);
    }

    public InvalidPatchBlog(String message, Throwable cause) {
        super(message, cause);
    }
}
