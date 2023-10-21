package com.pinku.validation.exception;

public class UserPersonException extends RuntimeException{
    private String message;

    public UserPersonException(String message){
        super(message);
    }
}
