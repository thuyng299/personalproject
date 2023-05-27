package com.nonit.personalproject.exception;

import org.springframework.http.HttpStatus;

public class NullInputProductIdException extends ResponseException{
    public NullInputProductIdException(String messageKey, String message, HttpStatus httpStatus) {
        super(messageKey, message, httpStatus);
    }
}
