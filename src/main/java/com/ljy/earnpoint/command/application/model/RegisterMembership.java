package com.ljy.earnpoint.command.application.model;

import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.MembershipType;
import com.ljy.earnpoint.command.domain.UserId;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * presentation layer 에서 받아올 멤버십 생성 값
 */
@Getter
public class RegisterMembership {
    @NotNull(message = "membership type must not be null")
    private MembershipType type;

    @Min(value = 0, message = "only 0 or more points are allowed")
    private long point;

    @Builder
    public RegisterMembership(MembershipType type, long point) {
        this.type = type;
        this.point = point;
    }

    public Membership create(UserId userid) {
        return type.create(point, userid);
    }
}
