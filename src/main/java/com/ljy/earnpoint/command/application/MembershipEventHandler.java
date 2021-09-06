package com.ljy.earnpoint.command.application;

import com.ljy.core.es.event.AbstractEventHandler;
import com.ljy.core.es.event.EventStore;
import com.ljy.core.es.snapshot.SnapshotRepository;
import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.MembershipId;
import org.springframework.stereotype.Component;

@Component
public class MembershipEventHandler extends AbstractEventHandler<Membership, MembershipId> {
    public MembershipEventHandler(EventStore<MembershipId> eventStore,
                                  SnapshotRepository<Membership, MembershipId> snapshotRepository) {
        super(eventStore, snapshotRepository);
    }
}
