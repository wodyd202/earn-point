package com.ljy.core.es.event;

public class EventListenerNotApplyException extends RuntimeException{
    public EventListenerNotApplyException(String message, Exception e) {
        super(message, e);
    }
}
