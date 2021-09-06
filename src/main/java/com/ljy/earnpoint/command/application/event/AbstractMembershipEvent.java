package com.ljy.earnpoint.command.application.event;

import com.ljy.core.es.event.Event;
import com.ljy.earnpoint.command.domain.MembershipId;

import java.io.Serializable;

public class AbstractMembershipEvent implements Event, Serializable {
    private final MembershipId membershipId;

    public AbstractMembershipEvent(MembershipId membershipId) {
        this.membershipId = membershipId;
    }

    public MembershipId getMembershipId() {
        return membershipId;
    }
}
