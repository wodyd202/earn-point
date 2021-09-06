package com.ljy.earnpoint.command.application;

import com.ljy.core.es.event.RawEvent;

import java.util.List;

public interface EventRepository<ID, E extends RawEvent> {
    long countByEmail(ID identifier);
    List<E> findByEmail(ID identifier);
    List<E> findByEmailAndVersion(ID identifier, Long version);
    void save(E event);
}
