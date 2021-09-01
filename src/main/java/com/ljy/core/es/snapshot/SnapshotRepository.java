package com.ljy.core.es.snapshot;

import com.ljy.core.es.domain.AggregateRoot;

import java.util.Optional;

public interface SnapshotRepository<A extends AggregateRoot, ID> {
    Optional<Snapshot<A, ID>> findLatest(ID identifier);

    void save(Snapshot<A, ID> snapshot);
}
