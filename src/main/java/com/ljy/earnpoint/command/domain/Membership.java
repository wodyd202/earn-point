package com.ljy.earnpoint.command.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ljy.core.es.domain.AggregateRoot;
import com.ljy.earnpoint.command.application.event.RegisteredMembershipEvent;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.ljy.earnpoint.command.domain.MembershipState.ACTIVE;

/**
 * 멤버십
 */
@JsonTypeInfo(
        use=JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = HappyPoint.class, name = "happypoint")
})
abstract public class Membership extends AggregateRoot<MembershipId> {

    /**
     * membershipId 멤버십 고유 번호
     * point 멤버십 포인트
     * state 멤버십 상태
     * createDateTime 멤버십 생성일
     * userId 멤버십 주인
     */
    protected Point point;
    protected MembershipState state;
    protected LocalDateTime createDateTime;
    protected UserId userId;

    protected Membership(){super(null);}
    protected Membership(Point point, UserId userId) {
        super(MembershipId.newInstance());
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

    /**
     * @param validator
     * - 멤버십 생성
     */
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
        return Objects.equals(point, that.point) && state == that.state && Objects.equals(createDateTime, that.createDateTime) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, state, createDateTime, userId);
    }
}
