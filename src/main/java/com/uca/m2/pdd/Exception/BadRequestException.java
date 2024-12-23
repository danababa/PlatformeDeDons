package com.uca.m2.pdd.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    public BadRequestException () {
        super(HttpStatus.BAD_REQUEST);
    }

    public BadRequestException (String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
