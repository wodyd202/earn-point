package com.ljy.core.es.event;

public class EventApplyException extends RuntimeException{
    public EventApplyException() {
    }

    public EventApplyException(String message) {
        super(message);
    }

    public EventApplyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EventApplyException(Throwable cause) {
        super(cause);
    }

    public EventApplyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
