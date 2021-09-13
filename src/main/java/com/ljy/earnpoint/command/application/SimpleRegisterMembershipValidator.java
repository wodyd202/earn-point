package com.ljy.earnpoint.command.application;

import com.ljy.earnpoint.domain.Membership;
import com.ljy.earnpoint.domain.RegisterMembershipValidator;
import com.ljy.earnpoint.domain.exception.AlreadyExistMembershipException;
import com.ljy.earnpoint.domain.values.UserId;
import org.springframework.stereotype.Component;

@Component
public class SimpleRegisterMembershipValidator implements RegisterMembershipValidator {
    private final MembershipRepository membershipRepository;

    public SimpleRegisterMembershipValidator(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public <T extends Membership> void validation(Class<T> membershipType, UserId userId) {
        if(membershipRepository.existByUserIdAndType(userId, membershipType.getSimpleName())){
            throw new AlreadyExistMembershipException("already exist membership type");
        }
    }
}
