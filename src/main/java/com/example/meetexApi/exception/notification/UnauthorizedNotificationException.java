package com.example.meetexApi.exception.notification;

public class UnauthorizedNotificationException extends RuntimeException {
    public UnauthorizedNotificationException(String message) {
        super(message);
    }
}
