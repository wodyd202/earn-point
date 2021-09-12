package com.ljy.earnpoint.command.application;

import com.ljy.core.es.event.AbstractEventHandler;
import com.ljy.core.es.event.EventStore;
import com.ljy.core.es.snapshot.Snapshot;
import com.ljy.core.es.snapshot.SnapshotRepository;
import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.MembershipId;
import com.ljy.earnpoint.command.domain.es.MembershipSnapshot;
import org.springframework.stereotype.Component;

@Component
public class MembershipEventHandler extends AbstractEventHandler<Membership, MembershipId> {
    public MembershipEventHandler(EventStore<MembershipId> eventStore,
                                  SnapshotRepository<Membership, MembershipId> snapshotRepository) {
        super(eventStore, snapshotRepository);
    }

    @Override
    protected Snapshot<Membership, MembershipId> newSnapshot(Membership aggregate) {
        return new MembershipSnapshot(aggregate.getIdentifier(), aggregate.getExpectedVersion(), aggregate);
    }
}
