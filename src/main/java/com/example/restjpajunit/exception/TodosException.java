package com.example.restjpajunit.exception;

public class TodosException extends Exception {

    private static final long serialVersionUID = -7535427759706770757L;

    private String message;

    public TodosException(String message) {
        super(message);
        this.message = message;
    }

    public TodosException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
