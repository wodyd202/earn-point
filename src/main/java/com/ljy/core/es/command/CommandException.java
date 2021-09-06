package com.ljy.core.es.command;

import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CommandException extends IllegalArgumentException {
    private Errors errors;
    public CommandException(Errors errors) {
        this.errors = errors;
    }

    public List<String> getMessages() {
        return errors.getAllErrors().stream().map(c->c.getDefaultMessage()).collect(toList());
    }
}
