package com.ljy.earnpoint.core.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommandExceptionHandler {

    @ExceptionHandler(CommandException.class)
    public APIResponse error(CommandException e){
        return new APIResponse(e.getAllMessages(), HttpStatus.BAD_REQUEST);
    }
}
