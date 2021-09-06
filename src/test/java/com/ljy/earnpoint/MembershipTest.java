package com.ljy.earnpoint;

import com.ljy.core.es.event.EventStore;
import com.ljy.core.es.snapshot.SnapshotRepository;
import com.ljy.earnpoint.command.application.MembershipEventHandler;
import com.ljy.earnpoint.command.application.RegisterMembershipService;
import com.ljy.earnpoint.command.application.model.RegisterMembership;
import com.ljy.earnpoint.command.domain.*;
import com.ljy.earnpoint.command.domain.exception.AlreadyExistMembershipException;
import com.ljy.earnpoint.command.domain.exception.InvalidPointException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.ljy.earnpoint.Fixture.aMembership;
import static com.ljy.earnpoint.command.domain.MembershipState.ACTIVE;
import static com.ljy.earnpoint.command.domain.MembershipType.HAPPY_POINT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MembershipTest {

    @Test
    void invalidPoint(){
        Assertions.assertThrows(InvalidPointException.class, ()->{
            Point.won(-3000);
        });
    }

    @Test
    void create() {
        RegisterMembership registerMembership = RegisterMembership.builder()
                .type(HAPPY_POINT)
                .point(3000)
                .build();
        UserId userid = UserId.of("userid");
        Membership membership = registerMembership.create(userid);

        assertTrue(membership instanceof HappyPoint);
        assertEquals(membership.getPoint(), Point.won(3000));
    }

    @Test
    void register(){
        Membership membership = aMembership(3000, UserId.of("userid"), HAPPY_POINT);

        MembershipRepository membershipRepository = mock(MembershipRepository.class);
        RegisterMembershipValidator validator = new RegisterMembershipValidator(membershipRepository);
        membership.register(validator);
        assertEquals(membership.getState(), ACTIVE);
    }

    @Test
    @DisplayName("이미 해당 종류의 멤버십이 존재하면 안됨")
    void alreadyExistType(){
        Membership membership = aMembership(3000, UserId.of("userid"), HAPPY_POINT);
        MembershipRepository membershipRepository = mock(MembershipRepository.class);

        when(membershipRepository.existByMembershipTypeAndUserId(membership.getClass(), UserId.of("userid")))
                .thenReturn(true);

        RegisterMembershipValidator validator = new RegisterMembershipValidator(membershipRepository);
        assertThrows(AlreadyExistMembershipException.class,()->{
            membership.register(validator);
        });
    }

    @Nested
    @SpringBootTest
    class RegisterMembershipService_Test {
        @Autowired RegisterMembershipService service;

        @Test
        void register(){
            RegisterMembership registerMembership = RegisterMembership.builder()
                    .type(HAPPY_POINT)
                    .point(3000)
                    .build();
            Membership membership = service.register(registerMembership, UserId.of("userid"));
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
