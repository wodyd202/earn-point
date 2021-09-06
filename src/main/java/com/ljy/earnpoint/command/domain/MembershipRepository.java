package com.ljy.earnpoint.command.domain;

import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.UserId;

public interface MembershipRepository {
    boolean existByMembershipTypeAndUserId(Class<? extends Membership> membership, UserId userId);
}
