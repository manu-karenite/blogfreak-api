package com.blogfreak.blog_freak_api.exception;

public class HealthCheckFail extends RuntimeException {
    public HealthCheckFail() {}

    public HealthCheckFail(String message) {
        super(message);
    }

    public HealthCheckFail(String message, Throwable cause) {
        super(message, cause);
    }
}
