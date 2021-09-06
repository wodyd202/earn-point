package com.ljy.earnpoint;

import com.ljy.earnpoint.command.application.model.RegisterMembership;
import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.MembershipType;
import com.ljy.earnpoint.command.domain.UserId;

import static com.ljy.earnpoint.command.domain.MembershipType.HAPPY_POINT;

public class Fixture {
    public static RegisterMembership.RegisterMembershipBuilder aRegisterMembership(){
        return RegisterMembership.builder()
                .type(HAPPY_POINT)
                .point(0);
    }

    public static Membership aMembership(long point, UserId userId, MembershipType type) {
        return type.create(3000, userId);
    }
}
