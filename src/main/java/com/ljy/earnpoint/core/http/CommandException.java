package com.ljy.earnpoint.core.http;

import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

public class CommandException extends IllegalArgumentException{
    private final Errors errors;

    public CommandException(Errors errors) {
        this.errors = errors;
    }

    public List<String> getAllMessages() {
        return errors.getAllErrors().stream().map(c->c.getDefaultMessage()).collect(Collectors.toList());
    }
}
