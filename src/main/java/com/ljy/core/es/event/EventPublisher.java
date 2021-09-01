package com.ljy.core.es.event;

@FunctionalInterface
public interface EventPublisher<T extends RawEvent>{
    void publish(T rawEvent);
}
