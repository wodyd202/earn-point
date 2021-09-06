package com.ljy.earnpoint.command.infrastructure;

import com.ljy.core.es.snapshot.Snapshot;
import com.ljy.core.es.snapshot.SnapshotRepository;
import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.MembershipId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MembershipSnapshotRepository implements SnapshotRepository<Membership, MembershipId> {

    @Override
    public Optional<Snapshot<Membership, MembershipId>> findLatest(MembershipId identifier) {
        return Optional.empty();
    }

    @Override
    public void save(Snapshot<Membership, MembershipId> snapshot) {
    }
}
