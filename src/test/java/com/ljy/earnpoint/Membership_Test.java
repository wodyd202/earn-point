package com.ljy.earnpoint;

import com.ljy.earnpoint.command.application.SimpleRegisterMembershipValidator;
import com.ljy.earnpoint.command.application.MembershipRepository;
import com.ljy.earnpoint.domain.RegisterMembership;
import com.ljy.earnpoint.domain.*;
import com.ljy.earnpoint.domain.exception.AlreadyAbledMembershipException;
import com.ljy.earnpoint.domain.exception.AlreadyEnabledMembershipException;
import com.ljy.earnpoint.domain.exception.AlreadyExistMembershipException;
import com.ljy.earnpoint.domain.exception.InvalidPointException;
import com.ljy.earnpoint.domain.read.MembershipModel;
import com.ljy.earnpoint.domain.values.MembershipState;
import com.ljy.earnpoint.domain.values.Point;
import com.ljy.earnpoint.domain.values.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ljy.earnpoint.Fixture.aHappyPoint;
import static com.ljy.earnpoint.domain.values.MembershipType.HAPPY_POINT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Membership_Test {

    @Test
    void invalidPoint(){
        Assertions.assertThrows(InvalidPointException.class, ()->{
            Point.won(-3000);
        });
    }

    @Test
    void validPoint(){
        Point won = Point.won(3000);
        assertEquals(won, Point.won(3000));
        assertEquals(won.get(), 3000);
    }

    @Test
    @DisplayName("해피포인트 멤버십 생성")
    void create() {
        RegisterMembership registerMembership = RegisterMembership.builder()
                .type(HAPPY_POINT)
                .point(3000)
                .build();
        Membership membership = Membership.createWith(registerMembership, UserId.of("userId"));
        assertTrue(membership instanceof HappyPoint);
    }

    @Test
    @DisplayName("해피포인트 멤버십 정상 등록")
    void register_happypoint(){
        RegisterMembership registerMembership = RegisterMembership.builder()
                .type(HAPPY_POINT)
                .point(3000)
                .build();
        Membership membership = Membership.createWith(registerMembership, UserId.of("userId"));

        MembershipRepository membershipRepository = mock(MembershipRepository.class);
        RegisterMembershipValidator validator = new SimpleRegisterMembershipValidator(membershipRepository);
        membership.register(validator);
        MembershipModel membershipModel = membership.toModel();
        assertEquals(membershipModel.getState(), MembershipState.ACTIVE);
    }

    @Test
    @DisplayName("멤버십 생성시 기존에 해당 타입의 멤버십이 있으면 안됨")
    void register_alreadyExist(){
        MembershipRepository membershipRepository = mock(MembershipRepository.class);
        RegisterMembershipValidator validator = new SimpleRegisterMembershipValidator(membershipRepository);
        when(membershipRepository.existByUserIdAndType(UserId.of("userId"), "HappyPoint"))
                .thenReturn(true);

        assertThrows(AlreadyExistMembershipException.class,()->{
            validator.validation(HappyPoint.class, UserId.of("userId"));
        });
    }


    @Test
    @DisplayName("멤버십 비활성화")
    void enable(){
        Membership membership = aHappyPoint();
        membership.enable();
        MembershipModel membershipModel = membership.toModel();
        assertEquals(membershipModel.getState(), MembershipState.UN_ACTIVE);
    }

    @Test
    @DisplayName("이미 비활성화되어 있는 멤버십을 다시 비활성화 할 수 없음")
    void enable_alreadyenabled(){
        Membership membership = aHappyPoint();
        membership.enable();
        assertThrows(AlreadyEnabledMembershipException.class, ()->{
            membership.enable();
        });
    }

    @Test
    @DisplayName("멤버십 활성화")
    void able(){
        Membership membership = aHappyPoint();
        membership.enable();
        membership.able();
        MembershipModel membershipModel = membership.toModel();
        assertEquals(membershipModel.getState(), MembershipState.ACTIVE);
    }

    @Test
    @DisplayName("이미 활성화되어 있는 멤버십을 다시 활성화 할 수 없음")
    void able_alreadyAbled(){
        Membership membership = aHappyPoint();
        assertThrows(AlreadyAbledMembershipException.class,()->{
           membership.able();
        });
    }

}
