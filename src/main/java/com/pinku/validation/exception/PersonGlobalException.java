package com.pinku.validation.exception;


import com.pinku.validation.error.PersonErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class PersonGlobalException {

    Date ld = new Date();
    @ExceptionHandler(UserPersonException.class)
    public ResponseEntity<PersonErrorResponse> handleUserPersonException(UserPersonException ex){


        PersonErrorResponse personError = new PersonErrorResponse(
                ex.getMessage(), HttpStatus.BAD_REQUEST.value(), "Forbidden",ld.getTime()
        );

        return new ResponseEntity<>(personError,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<PersonErrorResponse> handleNoDataFoundException(NoDataFoundException ex){


        PersonErrorResponse personError = new PersonErrorResponse(
                ex.getMessage(), HttpStatus.NOT_FOUND.value(), "Forbidden",ld.getTime()
        );

        return new ResponseEntity<>(personError,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return errorMap;
    }
}
