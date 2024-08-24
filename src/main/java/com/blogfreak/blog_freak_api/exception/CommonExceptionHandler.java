package com.blogfreak.blog_freak_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> handleResponseForCategoryNotFound(CategoryNotFound e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> handleResponseForBloggerNotFound(BloggerNotFound e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> handleResponseForBlogNotFound(BlogNotFound e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> handleResponseForInvalidGender(InvalidGender e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> handleResponseForInvalidPatchBlogger(InvalidPatchBlogger e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> handleResponseForInvalidLike(InvalidLikeException e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> handleResponseForInvalidPatchBlog(InvalidPatchBlog e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> handleResponseForDuplicateBlogger(DuplicateEntity e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> handleHealthCheckFailException(HealthCheckFail e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> unauthorizedExceptionResponse(UnauthorizedException e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> forbiddenExceptionResponse(ForbiddenException e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.FORBIDDEN, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> genericException(Exception e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<GlobalExceptionResponse> handleDTOValidation(MethodArgumentNotValidException e) {
        GlobalExceptionResponse globalExceptionResponse =
                new GlobalExceptionResponse(HttpStatus.BAD_REQUEST, e.getDetailMessageArguments()[1].toString());
        return new ResponseEntity<>(globalExceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
