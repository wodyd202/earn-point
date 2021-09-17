package com.ljy.earnpoint.command.application;

import com.ljy.earnpoint.domain.Membership;
import com.ljy.earnpoint.domain.read.MembershipModel;
import com.ljy.earnpoint.domain.values.MembershipId;
import com.ljy.earnpoint.domain.values.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ljy.earnpoint.command.application.MembershipServiceHelper.findByMembershipIdAndUserId;

@Service
@Transactional
public class AbleMembershipService {
    private final MembershipRepository membershipRepository;

    public AbleMembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public MembershipModel able(MembershipId membershipId, UserId userId) {
        Membership membership = findByMembershipIdAndUserId(membershipRepository, membershipId, userId);
        membership.able();
        membershipRepository.save(membership);
        return membership.toModel();
    }
}
