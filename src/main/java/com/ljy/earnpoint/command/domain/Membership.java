package com.ljy.earnpoint.command.domain;

import com.ljy.core.es.domain.AggregateRoot;
import com.ljy.earnpoint.command.application.event.RegisteredMembershipEvent;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.ljy.earnpoint.command.domain.MembershipState.ACTIVE;

abstract public class Membership extends AggregateRoot<MembershipId> {
    protected MembershipId membershipId;
    protected Point point;
    protected MembershipState state;
    protected LocalDateTime createDateTime;
    protected UserId userId;

    protected Membership(){super(null);}
    protected Membership(Point point, UserId userId) {
        super(MembershipId.newInstance());
        membershipId = getIdentifier();
        this.point = point;
        this.userId = userId;
        createDateTime = LocalDateTime.now();
    }

    public Point getPoint() {
        return point;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void register(RegisterMembershipValidator validator){
        validator.validation(this.getClass(), userId);
        state = ACTIVE;
        applyChange(new RegisteredMembershipEvent(this));
    }

    abstract void apply(RegisteredMembershipEvent event);

    public MembershipState getState() {
        return state;
    }

    public UserId getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membership that = (Membership) o;
        return Objects.equals(membershipId, that.membershipId) && Objects.equals(point, that.point) && state == that.state && Objects.equals(createDateTime, that.createDateTime) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(membershipId, point, state, createDateTime, userId);
    }
}
