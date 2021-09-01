package com.ljy.core.es.event;

@FunctionalInterface
public interface EventProjector {
    void handle(Event event);
}
