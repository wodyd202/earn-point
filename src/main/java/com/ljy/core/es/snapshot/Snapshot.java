package com.ljy.core.es.snapshot;

import com.ljy.core.es.domain.AggregateRoot;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
abstract public class Snapshot<A extends AggregateRoot, ID extends Serializable> {

    @Id
    private final ID identifier;
    private final long version;

    protected Snapshot(){
        this.identifier = null;
        this.version = 0;
    }

    protected Snapshot(ID identifier, long version) {
        this.identifier = identifier;
        this.version = version;
    }

    public ID getIdentifier() {
        return identifier;
    }

    public long getVersion() {
        return version;
    }

    abstract public A getAggregateRoot();
}
