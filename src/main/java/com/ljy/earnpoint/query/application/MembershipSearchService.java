package com.ljy.earnpoint.query.application;

import com.ljy.earnpoint.query.model.Membership;
import com.ljy.earnpoint.query.model.MembershipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MembershipSearchService {
    private final MembershipRepository membershipRepository;

    public MembershipSearchService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public List<Membership> getAllMembership(String userId){
        return membershipRepository.findByUserId(userId);
    }
}
