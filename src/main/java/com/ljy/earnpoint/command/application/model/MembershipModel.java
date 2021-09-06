package com.ljy.earnpoint.command.application.model;

import com.ljy.earnpoint.command.domain.Membership;
import lombok.Builder;

import java.time.LocalDateTime;

public class MembershipModel {
    private String membershipId;
    private long point;
    private String state;
    private LocalDateTime createDateTime;
    private String userId;

    @Builder
    private MembershipModel(String membershipId, long point, String state, LocalDateTime createDateTime, String userId) {
        this.membershipId = membershipId;
        this.point = point;
        this.state = state;
        this.createDateTime = createDateTime;
        this.userId = userId;
    }

    public static MembershipModel of(Membership membership) {
        return MembershipModel.builder()
                .membershipId(membership.getIdentifier().get().toString())
                .point(membership.getPoint().get())
                .state(membership.getState().toString())
                .createDateTime(membership.getCreateDateTime())
                .userId(membership.getUserId().get())
                .build();
    }
}
