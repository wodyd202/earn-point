package com.ljy.earnpoint.command.application;

import com.ljy.core.es.event.EventPublisher;
import com.ljy.earnpoint.command.application.event.MembershipRawEvent;
import org.springframework.stereotype.Component;

@Component
public class MembershipEventPublisher implements EventPublisher<MembershipRawEvent> {
    @Override
    public void publish(MembershipRawEvent rawEvent) {
    }
}
