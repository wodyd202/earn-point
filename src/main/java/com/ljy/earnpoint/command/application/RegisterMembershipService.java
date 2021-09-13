package com.ljy.earnpoint.command.application;

import com.ljy.earnpoint.command.application.model.RegisterMembership;
import com.ljy.earnpoint.domain.Membership;
import com.ljy.earnpoint.domain.read.MembershipModel;
import com.ljy.earnpoint.domain.values.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterMembershipService {
    private final MembershipRepository membershipRepository;
    private final SimpleRegisterMembershipValidator registerMembershipValidator;

    public RegisterMembershipService(MembershipRepository membershipRepository, SimpleRegisterMembershipValidator registerMembershipValidator) {
        this.membershipRepository = membershipRepository;
        this.registerMembershipValidator = registerMembershipValidator;
    }

    public MembershipModel register(RegisterMembership registerMembership, UserId userId) {
        Membership membership = Membership.createWith(registerMembership, userId);
        membership.register(registerMembershipValidator);
        membershipRepository.save(membership);
        return membership.toModel();
    }
}

