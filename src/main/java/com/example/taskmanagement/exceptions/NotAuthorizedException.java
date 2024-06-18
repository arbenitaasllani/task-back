package com.example.taskmanagement.exceptions;

public class NotAuthorizedException extends ApiRuntimeException{
    public NotAuthorizedException() {
    }

    public NotAuthorizedException(String message) {
        super(message);
    }
}