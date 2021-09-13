package com.ljy.earnpoint;

import com.ljy.earnpoint.command.application.model.RegisterMembership;
import com.ljy.earnpoint.domain.HappyPoint;
import com.ljy.earnpoint.domain.RegisterMembershipValidator;

import static com.ljy.earnpoint.domain.values.MembershipType.HAPPY_POINT;
import static org.mockito.Mockito.mock;

public class Fixture {
    public static RegisterMembership.RegisterMembershipBuilder aRegisterMembership(){
        return RegisterMembership.builder()
                .type(HAPPY_POINT)
                .point(0);
    }

    public static HappyPoint aHappyPoint(){
        HappyPoint happyPoint = HappyPoint.builder().build();
        happyPoint.register(mock(RegisterMembershipValidator.class));
        return happyPoint;
    }
}
