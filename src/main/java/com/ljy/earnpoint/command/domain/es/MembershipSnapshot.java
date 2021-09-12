package com.ljy.earnpoint.command.domain.es;

import com.ljy.core.es.snapshot.Snapshot;
import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.MembershipId;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "membership_snapshot")
public class MembershipSnapshot extends Snapshot<Membership, MembershipId> {

    @Convert(converter = MembershipConverter.class)
    private final Membership aggregateRoot;

    protected MembershipSnapshot(){
        super(null,0);
        aggregateRoot = null;
    }

    @Override
    public Membership getAggregateRoot() {
        return aggregateRoot;
    }

    public MembershipSnapshot(MembershipId identifier, long version, Membership aggregateRoot) {
        super(identifier,version);
        this.aggregateRoot = aggregateRoot;
    }
}
