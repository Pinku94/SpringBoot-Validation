package com.pinku.validation.exception;

public class userPersonException extends RuntimeException{
    private String message;

    public  userPersonException(String message){
        super(message);
    }
}
