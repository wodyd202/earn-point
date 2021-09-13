package com.ljy.earnpoint.query.application;

import com.ljy.earnpoint.domain.read.MembershipModel;

import java.util.List;

public interface MembershipSearchRepository {
    List<MembershipModel> findByUserId(String userId);
}
