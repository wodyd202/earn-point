package com.ljy.earnpoint.query.model;

public interface MembershipRepository {
    Membership findByMembershipId(String membershipId);
    void save(Membership membership);
}
