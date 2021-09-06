package com.ljy.earnpoint.command.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.core.es.event.*;
import com.ljy.core.es.event.EventRepository;
import com.ljy.earnpoint.command.application.event.MembershipRawEvent;
import com.ljy.earnpoint.command.domain.MembershipId;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MembershipEventStore extends AbstractEventStore<MembershipId> {

    protected MembershipEventStore(EventRepository eventStoreRepository, EventPublisher eventPublisher, EventProjector eventProjector, ObjectMapper objectMapper) {
        super(eventStoreRepository, eventPublisher, eventProjector, objectMapper);
    }

    @Override
    protected RawEvent<String> getRawEvent(MembershipId identifier, String type, String payload, Long version) {
        return MembershipRawEvent.builder()
                .identifiier(identifier.get().toString())
                .type(type)
                .payload(payload)
                .version(version)
                .createDateTime(LocalDateTime.now())
                .build();
    }
}
