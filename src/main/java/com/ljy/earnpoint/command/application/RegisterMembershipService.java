package com.ljy.earnpoint.command.application;

import com.ljy.earnpoint.command.application.model.MembershipModel;
import com.ljy.earnpoint.command.application.model.RegisterMembership;
import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.RegisterMembershipValidator;
import com.ljy.earnpoint.command.domain.UserId;
import org.springframework.stereotype.Service;

@Service
public class RegisterMembershipService {
    private final MembershipEventHandler membershipEventHandler;
    private final RegisterMembershipValidator registerMembershipValidator;

    public RegisterMembershipService(MembershipEventHandler membershipEventHandler,
                                     RegisterMembershipValidator registerMembershipValidator) {
        this.membershipEventHandler = membershipEventHandler;
        this.registerMembershipValidator = registerMembershipValidator;
    }


    public MembershipModel register(RegisterMembership registerMembership, UserId userId) {
        Membership membership = registerMembership.create(userId);
        membership.register(registerMembershipValidator);
        membershipEventHandler.save(membership);
        return MembershipModel.of(membership);
    }
}
