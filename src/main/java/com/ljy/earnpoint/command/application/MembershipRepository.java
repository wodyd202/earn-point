package com.ljy.earnpoint.command.application;

import com.ljy.earnpoint.domain.Membership;
import com.ljy.earnpoint.domain.values.MembershipId;
import com.ljy.earnpoint.domain.values.UserId;

import java.util.Optional;

public interface MembershipRepository {
    boolean existByUserIdAndType(UserId userId, String type);
    void save(Membership membership);

    Optional<Membership> findByMembershipIdAndUserId(MembershipId membershipId, UserId userId);
}
