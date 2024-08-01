package com.blogfreak.blog_freak_api.oas.schema.error;

import java.util.Date;
import org.springframework.http.HttpStatus;

public class BaseException {
    private Date timestamp;
    private HttpStatus statusCodeMessage;
    private int statusCode;
    private String message;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatusCodeMessage() {
        return statusCodeMessage;
    }

    public void setStatusCodeMessage(HttpStatus statusCodeMessage) {
        this.statusCodeMessage = statusCodeMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
