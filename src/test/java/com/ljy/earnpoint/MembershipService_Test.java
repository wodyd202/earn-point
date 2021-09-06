package com.ljy.earnpoint;

import com.ljy.earnpoint.command.application.RegisterMembershipService;
import com.ljy.earnpoint.command.application.model.MembershipModel;
import com.ljy.earnpoint.command.application.model.RegisterMembership;
import com.ljy.earnpoint.command.domain.UserId;
import com.ljy.earnpoint.command.domain.exception.AlreadyExistMembershipException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ljy.earnpoint.command.domain.MembershipType.HAPPY_POINT;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MembershipService_Test {

    @Nested
    @SpringBootTest
    class RegisterMembershipService_Test {
        @Autowired
        RegisterMembershipService service;

        @Test
        void register(){
            RegisterMembership registerMembership = RegisterMembership.builder()
                    .type(HAPPY_POINT)
                    .point(3000)
                    .build();
            MembershipModel membership = service.register(registerMembership, UserId.of("userid"));
            assertNotNull(membership);
        }

        @Test
        void alreadyExistType() {
            RegisterMembership registerMembership = RegisterMembership.builder()
                    .type(HAPPY_POINT)
                    .point(3000)
                    .build();

            assertThrows(AlreadyExistMembershipException.class,()->{
                service.register(registerMembership, UserId.of("alreadyExist"));
                service.register(registerMembership, UserId.of("alreadyExist"));
            });
        }
    }
}
