package com.blogfreak.blog_freak_api.oas.schema.success;

import java.util.Date;
import org.springframework.http.HttpStatus;

public class BaseSuccess {
    private Date timestamp;
    private HttpStatus statusCodeMessage;
    private int statusCode;

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
}
