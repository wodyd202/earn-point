package com.ljy.earnpoint;

import com.ljy.earnpoint.command.application.RegisterMembershipService;
import com.ljy.earnpoint.command.application.model.RegisterMembership;
import com.ljy.earnpoint.domain.read.MembershipModel;
import com.ljy.earnpoint.domain.values.UserId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ljy.earnpoint.Fixture.aRegisterMembership;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RegisterMembershipService_Test {
    @Autowired private RegisterMembershipService registerMembershipService;

    @Test
    void register(){
        RegisterMembership registerMembership = aRegisterMembership().build();
        MembershipModel membershipModel = registerMembershipService.register(registerMembership, UserId.of("userId"));
        assertNotNull(membershipModel);
    }
}
