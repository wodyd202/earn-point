package com.ljy.earnpoint;

import com.ljy.earnpoint.command.application.MembershipRepository;
import com.ljy.earnpoint.domain.Membership;
import com.ljy.earnpoint.domain.exception.MembershipNotFoundException;
import com.ljy.earnpoint.domain.values.MembershipId;
import com.ljy.earnpoint.domain.values.UserId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnableMembershipService {
    private final MembershipRepository membershipRepository;

    public EnableMembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public void enable(MembershipId membershipId, UserId userId) {
        Membership membership = membershipRepository.findByMembershipIdAndUserId(membershipId, userId)
                .orElseThrow(MembershipNotFoundException::new);
        membership.enable();
        membershipRepository.save(membership);
    }
}
