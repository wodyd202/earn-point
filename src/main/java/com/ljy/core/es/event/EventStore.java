package com.ljy.core.es.event;

import java.util.List;

public interface EventStore<ID> {
    List<Event> getEventsByAfterVersion(ID identifier, long version);

    List<Event> getEvents(ID identifier);

    void saveEvents(ID identifier, Long expectedVersion, List<Event> unCommitedChange);

    long countEvents(ID identifier);
}
