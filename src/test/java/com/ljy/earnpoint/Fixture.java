package com.ljy.earnpoint;

import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.MembershipType;
import com.ljy.earnpoint.command.domain.UserId;

public class Fixture {

    public static Membership aMembership(long point, UserId userId, MembershipType type) {
        return type.create(3000, userId);
    }
}
