package com.example.rediscache.exception;

public class NoValueForThisKey extends RuntimeException{
    public NoValueForThisKey(String message) {
        super(message);
    }
}
