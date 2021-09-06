package com.ljy.earnpoint.command.application.model;

import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.MembershipType;
import com.ljy.earnpoint.command.domain.UserId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RegisterMembership {
    private MembershipType type;
    private long point;

    protected RegisterMembership(){}

    @Builder
    public RegisterMembership(MembershipType type, long point) {
        this.type = type;
        this.point = point;
    }

    public Membership create(UserId userid) {
        return type.create(point, userid);
    }
}
