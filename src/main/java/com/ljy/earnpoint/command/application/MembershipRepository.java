package com.ljy.earnpoint.command.application;

import com.ljy.earnpoint.domain.Membership;
import com.ljy.earnpoint.domain.values.UserId;

public interface MembershipRepository {
    boolean existByUserIdAndType(UserId userId, String type);
    void save(Membership membership);
}
