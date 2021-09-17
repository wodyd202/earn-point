package com.ljy.earnpoint.command.application;

import com.ljy.earnpoint.domain.Membership;
import com.ljy.earnpoint.domain.exception.MembershipNotFoundException;
import com.ljy.earnpoint.domain.values.MembershipId;
import com.ljy.earnpoint.domain.values.UserId;

public class MembershipServiceHelper {

    public static Membership findByMembershipIdAndUserId(MembershipRepository membershipRepository, MembershipId membershipId, UserId userId) {
        return membershipRepository.findByMembershipIdAndUserId(membershipId,userId).orElseThrow(MembershipNotFoundException::new);
    }
}
