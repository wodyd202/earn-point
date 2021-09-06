package com.ljy.earnpoint.command.domain;

public interface MembershipRepository {
    boolean existByMembershipTypeAndUserId(Class<? extends Membership> membership, UserId userId);
}
