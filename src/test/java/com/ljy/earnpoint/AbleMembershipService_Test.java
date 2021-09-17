package com.ljy.earnpoint;

import com.ljy.earnpoint.command.application.AbleMembershipService;
import com.ljy.earnpoint.command.application.EnableMembershipService;
import com.ljy.earnpoint.command.application.RegisterMembershipService;
import com.ljy.earnpoint.domain.Membership;
import com.ljy.earnpoint.domain.RegisterMembership;
import com.ljy.earnpoint.domain.read.MembershipModel;
import com.ljy.earnpoint.domain.values.MembershipId;
import com.ljy.earnpoint.domain.values.MembershipState;
import com.ljy.earnpoint.domain.values.UserId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ljy.earnpoint.Fixture.aRegisterMembership;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AbleMembershipService_Test {
    @Autowired RegisterMembershipService registerMembershipService;
    @Autowired AbleMembershipService ableMembershipService;
    @Autowired EnableMembershipService enableMembershipService;

    @Test
    void able(){
        RegisterMembership registerMembership = aRegisterMembership().build();
        MembershipModel membershipModel = registerMembershipService.register(registerMembership, UserId.of("able"));
        enableMembershipService.enable(MembershipId.of(membershipModel.getMembershipId()), UserId.of("able"));

        membershipModel = ableMembershipService.able(MembershipId.of(membershipModel.getMembershipId()), UserId.of("able"));

        assertEquals(membershipModel.getState(), MembershipState.ACTIVE);
    }
}
