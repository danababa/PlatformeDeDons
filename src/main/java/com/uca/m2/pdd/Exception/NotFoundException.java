package com.uca.m2.pdd.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {
    public NotFoundException () {
        super(HttpStatus.NOT_FOUND);
    }

    public NotFoundException (String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
