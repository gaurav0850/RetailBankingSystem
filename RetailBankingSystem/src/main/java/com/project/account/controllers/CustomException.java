package com.project.account.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomException extends Exception {

    public CustomException(String exception) {
        super(exception);
    }
}
