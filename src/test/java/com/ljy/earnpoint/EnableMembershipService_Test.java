package com.ljy.earnpoint;

import com.ljy.earnpoint.command.application.EnableMembershipService;
import com.ljy.earnpoint.command.application.RegisterMembershipService;
import com.ljy.earnpoint.domain.RegisterMembership;
import com.ljy.earnpoint.domain.read.MembershipModel;
import com.ljy.earnpoint.domain.values.MembershipId;
import com.ljy.earnpoint.domain.values.UserId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ljy.earnpoint.Fixture.aRegisterMembership;

@SpringBootTest
public class EnableMembershipService_Test {
    @Autowired RegisterMembershipService registerMembershipService;
    @Autowired EnableMembershipService enableMembershipService;

    @Test
    void enable(){
        RegisterMembership reigsterMembership = aRegisterMembership().build();
        MembershipModel enableUserId = registerMembershipService.register(reigsterMembership, UserId.of("enableUserId"));

        enableMembershipService.enable(MembershipId.of(enableUserId.getMembershipId()), UserId.of("enableUserId"));
    }
}
