package com.ljy.core.es.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

abstract public class AbstractEventStore<ID> implements EventStore<ID>{
    private final EventRepository eventStoreRepository;
    private final EventPublisher eventPublisher;
    private final EventProjector eventProjector;
    private final ObjectMapper objectMapper;

    protected AbstractEventStore(EventRepository eventStoreRepository, EventPublisher eventPublisher, EventProjector eventProjector, ObjectMapper objectMapper) {
        this.eventStoreRepository = eventStoreRepository;
        this.eventPublisher = eventPublisher;
        this.eventProjector = eventProjector;
        this.objectMapper = objectMapper;
    }

    abstract protected RawEvent<String> getRawEvent(ID identifier, String type, String payload, Long expectedVersion);

    @Override
    public List<Event> getEventsByAfterVersion(ID identifier, long version) {
        return eventStoreRepository.findAfterVersionEventByIdentifier(identifier, version);
    }

    @Override
    public List<Event> getEvents(ID identifier) {
        return eventStoreRepository.findByIdentifier(identifier);
    }

    @Override
    public void saveEvents(ID identifier, Long expectedVersion, List<Event> unCommitedChange) {
        if(expectedVersion > 0){
            List<RawEvent> events = eventStoreRepository.findByIdentifier(identifier);
            long actualVersion = events.get(events.size() - 1).getVersion() - 1;

            if(expectedVersion.equals(actualVersion)) {
                String exceptionMessage = String.format("Unmatched Version : expected: {}, actual: {}", expectedVersion, actualVersion);
                throw new IllegalStateException(exceptionMessage);
            }
        }
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        for (Event event : unCommitedChange) {
            String type = event.getClass().getName();
            try {
                String payload = objectMapper.writeValueAsString(event);
                expectedVersion = ++expectedVersion;
                RawEvent<String> rawEvent = getRawEvent(identifier,type,payload,expectedVersion);
                eventPublisher.publish(rawEvent);
                eventProjector.handle(event);
                eventStoreRepository.save(rawEvent);
            } catch (JsonProcessingException e) {}
        }
    }

    @Override
    public long countEvents(ID identifier) {
        return eventStoreRepository.countByIdentifier(identifier);
    }
}
