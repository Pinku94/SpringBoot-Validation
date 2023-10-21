package com.pinku.validation.exception;

public class NoDataFoundException extends RuntimeException{

    private String message;

    public NoDataFoundException(String message){

        super(message);
    }
}
