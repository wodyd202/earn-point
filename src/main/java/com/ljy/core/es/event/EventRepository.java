package com.ljy.core.es.event;

import java.util.List;

public interface EventRepository<ID, E extends RawEvent> {
    long countByIdentifier(ID identifier);
    List<E> findByIdentifier(ID identifier);
    List<E> findAfterVersionEventByIdentifier(ID identifier, Long version);
    void save(E event);
}
