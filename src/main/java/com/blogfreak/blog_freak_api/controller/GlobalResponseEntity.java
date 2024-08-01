package com.blogfreak.blog_freak_api.controller;

import java.util.Date;
import java.util.Objects;
import org.springframework.http.HttpStatus;

public class GlobalResponseEntity<T> {
    private Date timestamp;
    private HttpStatus statusCodeMessage;
    private int statusCode;
    private T data;

    public GlobalResponseEntity(HttpStatus statusCode, T data) {
        this.statusCodeMessage = statusCode;
        this.statusCode = statusCodeMessage.value();
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GlobalResponseEntity<?> that = (GlobalResponseEntity<?>) o;
        return statusCode == that.statusCode
                && Objects.equals(timestamp, that.timestamp)
                && statusCodeMessage == that.statusCodeMessage
                && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, statusCodeMessage, statusCode, data);
    }
}
