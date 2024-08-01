package com.blogfreak.blog_freak_api.exception;

import java.util.Date;
import java.util.Objects;
import org.springframework.http.HttpStatus;

public class GlobalExceptionResponse {
    private Date timestamp;
    private HttpStatus statusCodeMessage;
    private int statusCode;
    private String message;

    public GlobalExceptionResponse() {}

    public GlobalExceptionResponse(HttpStatus statusCodeMessage, String message) {
        this.statusCodeMessage = statusCodeMessage;
        this.statusCode = statusCodeMessage.value();
        this.message = message;
        this.timestamp = new Date();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GlobalExceptionResponse that = (GlobalExceptionResponse) o;
        return statusCode == that.statusCode
                && Objects.equals(timestamp, that.timestamp)
                && statusCodeMessage == that.statusCodeMessage
                && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, statusCodeMessage, statusCode, message);
    }

    @Override
    public String toString() {
        return "GlobalExceptionResponse{" + "timestamp="
                + timestamp + ", statusCodeMessage="
                + statusCodeMessage + ", statusCode="
                + statusCode + ", message='"
                + message + '\'' + '}';
    }
}
