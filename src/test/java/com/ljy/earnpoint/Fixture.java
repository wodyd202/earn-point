package com.ljy.earnpoint;

import com.ljy.earnpoint.domain.Membership;
import com.ljy.earnpoint.domain.RegisterMembership;
import com.ljy.earnpoint.domain.HappyPoint;
import com.ljy.earnpoint.domain.RegisterMembershipValidator;
import com.ljy.earnpoint.domain.values.UserId;

import static com.ljy.earnpoint.domain.values.MembershipType.HAPPY_POINT;
import static org.mockito.Mockito.mock;

public class Fixture {

    public static RegisterMembership.RegisterMembershipBuilder aRegisterMembership(){
        return RegisterMembership.builder()
                .type(HAPPY_POINT)
                .point(0);
    }

    public static HappyPoint aHappyPoint(){
        Membership membership = Membership.createWith(aRegisterMembership().type(HAPPY_POINT).build(), UserId.of("userId"));
        membership.register(mock(RegisterMembershipValidator.class));
        return (HappyPoint) membership;
    }
}
