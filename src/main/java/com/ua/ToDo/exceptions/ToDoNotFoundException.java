package com.ua.ToDo.exceptions;

public class ToDoNotFoundException extends Exception {

    public ToDoNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
