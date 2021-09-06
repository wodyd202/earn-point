package com.ljy.earnpoint.query.model;

import java.util.List;

public interface MembershipRepository {
    Membership findByMembershipId(String membershipId);
    void save(Membership membership);
    List<Membership> findByUserId(String userId);
}
