package com.ljy.earnpoint.command.application.event;

import com.ljy.core.es.event.Event;
import com.ljy.earnpoint.command.domain.MembershipId;

import java.io.Serializable;

/**
 * 멤버십 공통 이벤트
 */
public class AbstractMembershipEvent implements Event, Serializable {
    private final MembershipId membershipId;

    /**
     * @param membershipId 멤버십 고유 번호
     */
    public AbstractMembershipEvent(MembershipId membershipId) {
        this.membershipId = membershipId;
    }

    public MembershipId getMembershipId() {
        return membershipId;
    }
}
