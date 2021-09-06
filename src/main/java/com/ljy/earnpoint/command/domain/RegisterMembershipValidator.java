package com.ljy.earnpoint.command.domain;

import com.ljy.earnpoint.command.domain.exception.AlreadyExistMembershipException;
import org.springframework.stereotype.Component;

@Component
public class RegisterMembershipValidator {
    private final MembershipRepository membershipRepository;

    public RegisterMembershipValidator(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public void validation(Class<? extends Membership> membership, UserId userId) {
        if(existMembership(membership, userId)){
            throw new AlreadyExistMembershipException("already exist membership type");
        }
    }

    private boolean existMembership(Class<? extends Membership> membership, UserId userId) {
        return membershipRepository.existByMembershipTypeAndUserId(membership, userId);
    }
}
