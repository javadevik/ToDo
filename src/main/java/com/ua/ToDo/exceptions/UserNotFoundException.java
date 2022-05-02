package com.ua.ToDo.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String s) {
        super(s);
    }

    public UserNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
