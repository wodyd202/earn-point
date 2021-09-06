package com.ljy.earnpoint.command.application;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.core.es.event.*;
import com.ljy.earnpoint.command.application.EventRepository;
import com.ljy.earnpoint.command.application.event.MembershipRawEvent;
import com.ljy.earnpoint.command.domain.MembershipId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MembershipEventStore implements EventStore<MembershipId> {
    @Autowired private EventRepository eventStoreRepository;
    @Autowired private EventPublisher eventPublisher;
    @Autowired private EventProjector eventProjector;
    @Autowired private ObjectMapper objectMapper;

    @Override
    public List<Event> getEventsByAfterVersion(MembershipId identifier, long version) {
        return eventStoreRepository.findByEmailAndVersion(identifier, version);
    }

    @Override
    public List<Event> getEvents(MembershipId identifier) {
        return eventStoreRepository.findByEmail(identifier);
    }

    @Override
    public void saveEvents(MembershipId identifier, Long expectedVersion, List<Event> unCommitedChange) {
        if(expectedVersion > 0){
            List<RawEvent> events = eventStoreRepository.findByEmail(identifier);
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
                MembershipRawEvent membershipRawEvent = MembershipRawEvent.builder()
                        .identifiier(identifier.get().toString())
                        .type(type)
                        .version(expectedVersion)
                        .payload(payload)
                        .createDateTime(LocalDateTime.now())
                        .build();
                eventPublisher.publish(membershipRawEvent);
                eventProjector.handle(event);
                eventStoreRepository.save(membershipRawEvent);
            } catch (JsonProcessingException e) {}
        }
    }

    @Override
    public long countEvents(MembershipId identifier) {
        return eventStoreRepository.countByEmail(identifier);
    }
}
