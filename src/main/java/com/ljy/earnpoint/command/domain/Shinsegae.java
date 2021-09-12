package com.ljy.earnpoint.command.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ljy.earnpoint.command.application.event.RegisteredMembershipEvent;
import lombok.Builder;

@JsonTypeName("shinsegae")
public class Shinsegae extends Membership {

    @Builder
    private Shinsegae(Point point, UserId userId){
        super(point, userId);
    }

    @Override
    protected void apply(RegisteredMembershipEvent event){
        super.identifier = event.getMembershipId();
        point = event.getPoint();
        userId = event.getUserId();
        createDateTime = event.getCreateDateTime();
        state = event.getState();
    }
}
