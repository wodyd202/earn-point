package com.ljy.earnpoint.query.application;

import com.ljy.core.es.event.AbstractEventProjector;
import com.ljy.earnpoint.command.application.event.RegisteredMembershipEvent;
import com.ljy.earnpoint.query.model.Membership;
import com.ljy.earnpoint.query.model.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MembershipEventProjector extends AbstractEventProjector {
    @Autowired private MembershipRepository membershipRepository;

    protected void execute(RegisteredMembershipEvent event){
        Membership membership = new Membership(event);
        membershipRepository.save(membership);
    }
}
