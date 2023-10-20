package com.pinku.validation.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public class PersonErrorResponse {

    private String message;

    private int errorCode;

    private String errorDescription;
    private Long timestamp;

    public PersonErrorResponse(String message, int errorCode, String errorDescription, Long timestamp) {
        this.message = message;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
