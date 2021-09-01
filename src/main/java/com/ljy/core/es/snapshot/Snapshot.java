package com.ljy.core.es.snapshot;

import com.ljy.core.es.domain.AggregateRoot;

public class Snapshot<A extends AggregateRoot, ID> {
    private final ID identifier;
    private final long version;
    private final A aggregateRoot;

    public Snapshot(ID identifier, long version, A aggregateRoot) {
        this.identifier = identifier;
        this.version = version;
        this.aggregateRoot = aggregateRoot;
    }

    public ID getIdentifier() {
        return identifier;
    }

    public long getVersion() {
        return version;
    }

    public A getAggregateRoot() {
        return aggregateRoot;
    }
}
