package com.ljy.earnpoint.domain;

import com.ljy.earnpoint.domain.exception.AlreadyAbledMembershipException;
import com.ljy.earnpoint.domain.exception.AlreadyEnabledMembershipException;
import com.ljy.earnpoint.domain.read.MembershipModel;
import com.ljy.earnpoint.domain.values.*;
import com.ljy.earnpoint.domain.infra.PointConveter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "membership",indexes = {
        @Index(columnList = "userid")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@DynamicUpdate
abstract public class Membership extends AbstractAggregateRoot<Membership> {

    /**
     * membershipId 멤버십 고유 번호
     * point 멤버십 포인트
     * state 멤버십 상태
     * createDateTime 멤버십 생성일
     * userId 멤버십 주인
     */
    @EmbeddedId
    protected final MembershipId membershipId;

    @Convert(converter = PointConveter.class)
    @Column(nullable = false)
    protected Point point;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected MembershipState state;

    @Column(nullable = false)
    protected final LocalDateTime createDateTime;

    @Embedded
    @AttributeOverride(name = "id",column = @Column(name = "userid", nullable = false))
    protected UserId userId;

    protected Membership(){membershipId=null; createDateTime=null;}

    protected Membership(Point point, UserId userId) {
        this.point = point;
        this.userId = userId;
        membershipId = MembershipId.create();
        createDateTime = LocalDateTime.now();
    }

    public static Membership createWith(RegisterMembership registerMembership, UserId userId) {
        if (registerMembership.getType().equals(MembershipType.CJ_ONE)) {
            return CjOne.builder()
                    .point(Point.won(registerMembership.getPoint()))
                    .userId(userId)
                    .build();
        } else if (registerMembership.getType().equals(MembershipType.HAPPY_POINT)) {
            return HappyPoint.builder()
                    .point(Point.won(registerMembership.getPoint()))
                    .userId(userId)
                    .build();
        } else if (registerMembership.getType().equals(MembershipType.SHINSEGAE)) {
            return Shinsegae.builder()
                    .point(Point.won(registerMembership.getPoint()))
                    .userId(userId)
                    .build();
        }
        // 가능성 없음
        throw new AssertionError();
    }

    /**
     * @param validator
     * - 멤버십 등록
     */
    public void register(RegisterMembershipValidator validator){
        validator.validation(this.getClass(), userId);
        state = MembershipState.ACTIVE;
    }

    /**
     * - 멤버십 비활성화
     */
    public void enable(){
        verifyActive();
        state = MembershipState.UN_ACTIVE;
    }

    private void verifyActive(){
        if(state.equals(MembershipState.UN_ACTIVE)){
            throw new AlreadyEnabledMembershipException();
        }
    }

    public void able(){
        verifyUnActive();
        state = MembershipState.ACTIVE;
    }

    private void verifyUnActive(){
        if(state.equals(MembershipState.ACTIVE)){
            throw new AlreadyAbledMembershipException();
        }
    }


    public MembershipModel toModel(){
        return MembershipModel.builder()
                .membershipId(membershipId.get())
                .point(point.get())
                .state(state)
                .createDateTime(createDateTime)
                .userId(userId.get())
                .type(this.getClass())
                .build();
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
