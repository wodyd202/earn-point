package com.ljy.core.es.event;

import com.ljy.core.es.domain.AggregateRoot;

import java.util.Optional;

public interface EventHandler<A extends AggregateRoot, ID> {
    void save(A aggregate);
    Optional<A> find(ID identifier);
}
