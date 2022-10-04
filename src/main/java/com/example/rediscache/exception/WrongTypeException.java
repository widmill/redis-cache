package com.example.rediscache.exception;

public class WrongTypeException extends RuntimeException {
    public WrongTypeException(String message) {
        super(message);
    }
}
