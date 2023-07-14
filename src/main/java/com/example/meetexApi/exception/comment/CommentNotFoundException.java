package com.example.meetexApi.exception.comment;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
