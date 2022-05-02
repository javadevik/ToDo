package com.ua.ToDo.exceptions;

public class UserExistException extends Exception {

    public UserExistException(String s) {
        super(s);
    }

    public UserExistException(String s, Throwable throwable) {
        super(s, throwable);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
