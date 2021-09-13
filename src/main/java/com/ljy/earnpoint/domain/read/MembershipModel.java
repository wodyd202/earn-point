package com.ljy.earnpoint.domain.read;

import com.ljy.earnpoint.domain.values.MembershipId;
import com.ljy.earnpoint.domain.values.MembershipState;
import com.ljy.earnpoint.domain.values.Point;
import com.ljy.earnpoint.domain.values.UserId;
import lombok.Builder;

import java.time.LocalDateTime;

public class MembershipModel {
    private String membershipId;
    private long point;
    protected MembershipState state;
    protected LocalDateTime createDateTime;
    private String userId;
    private String type;

    public MembershipModel(MembershipId membershipId, Point point, String userId){
        this.membershipId = membershipId.get();
        this.point = point.get();
        this.userId = userId;
    }

    @Builder
    public MembershipModel(String membershipId,
                           long point,
                           MembershipState state,
                           LocalDateTime createDateTime,
                           String userId,
                           Class<?> type) {
        this.membershipId = membershipId;
        this.point = point;
        this.state = state;
        this.createDateTime = createDateTime;
        this.userId = userId;
        this.type = type.getSimpleName();
    }

    public String getMembershipId() {
        return membershipId;
    }

    public long getPoint() {
        return point;
    }

    public MembershipState getState() {
        return state;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public String getUserId() {
        return userId;
    }
}
