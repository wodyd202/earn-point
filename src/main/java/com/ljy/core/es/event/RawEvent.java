package com.ljy.core.es.event;

public interface RawEvent<ID> {
    ID getIdentifier();
    String getType();
    Long getVersion();
    String getPayload();
}
