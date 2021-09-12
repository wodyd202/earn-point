package com.ljy.core.es.snapshot;

import com.ljy.core.es.domain.AggregateRoot;

import java.io.Serializable;
import java.util.Optional;

public interface SnapshotRepository<A extends AggregateRoot, ID extends Serializable> {
    Optional<Snapshot<A, ID>> findLatest(ID identifier);

    <T extends Snapshot<A,ID>> void save(T snapshot);
}
