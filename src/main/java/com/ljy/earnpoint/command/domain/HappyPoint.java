package com.ljy.earnpoint.command.domain;

import com.ljy.earnpoint.command.application.event.RegisteredMembershipEvent;
import lombok.Builder;

public class HappyPoint extends Membership {

    @Builder
    private HappyPoint(Point point, UserId userId){
        super(point, userId);
    }

    @Override
    protected void apply(RegisteredMembershipEvent event){
        membershipId = event.getMembershipId();
        point = event.getPoint();
        userId = event.getUserId();
        createDateTime = event.getCreateDateTime();
        state = event.getState();
    }
}
