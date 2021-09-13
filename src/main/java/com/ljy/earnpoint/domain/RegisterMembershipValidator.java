package com.ljy.earnpoint.domain;

import com.ljy.earnpoint.domain.values.UserId;

public interface RegisterMembershipValidator {
    <T extends Membership> void validation(Class<T> membershipType, UserId userId);
}
