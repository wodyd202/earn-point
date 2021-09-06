package com.ljy.earnpoint.command.application.event;

import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.MembershipState;
import com.ljy.earnpoint.command.domain.Point;
import com.ljy.earnpoint.command.domain.UserId;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegisteredMembershipEvent extends AbstractMembershipEvent{
    private final Point point;
    private final Class<?> type;
    private final MembershipState state;
    private final LocalDateTime createDateTime;
    private final UserId userId;
    public RegisteredMembershipEvent(Membership membership) {
        super(membership.getIdentifier());
        point = membership.getPoint();
        state = membership.getState();
        createDateTime = membership.getCreateDateTime();
        userId = membership.getUserId();
        type = membership.getClass();
    }

    public Point getPoint() {
        return point;
    }

    public MembershipState getState() {
        return state;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public UserId getUserId() {
        return userId;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisteredMembershipEvent that = (RegisteredMembershipEvent) o;
        return Objects.equals(point, that.point) && state == that.state && Objects.equals(createDateTime, that.createDateTime) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, state, createDateTime, userId);
    }
}
