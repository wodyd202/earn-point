package com.ljy.earnpoint.domain;

import com.ljy.earnpoint.domain.values.MembershipType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
}
