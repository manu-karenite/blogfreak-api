package com.blogfreak.blog_freak_api.exception;

public class InvalidLikeException extends RuntimeException {
    public InvalidLikeException() {}

    public InvalidLikeException(String message) {
        super(message);
    }

    public InvalidLikeException(String message, Throwable cause) {
        super(message, cause);
    }
}
