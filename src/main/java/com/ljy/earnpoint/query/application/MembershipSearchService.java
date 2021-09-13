package com.ljy.earnpoint.query.application;

import com.ljy.earnpoint.domain.read.MembershipModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipSearchService {
    private final MembershipSearchRepository membershipRepository;

    public MembershipSearchService(MembershipSearchRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public List<MembershipModel> findByUserId(String userId) {
        return membershipRepository.findByUserId(userId);
    }
}
