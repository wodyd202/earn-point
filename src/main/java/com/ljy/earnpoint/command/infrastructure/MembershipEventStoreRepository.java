package com.ljy.earnpoint.command.infrastructure;

import com.ljy.core.es.event.EventRepository;
import com.ljy.earnpoint.command.application.event.MembershipRawEvent;
import com.ljy.earnpoint.command.domain.MembershipId;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.ljy.earnpoint.command.application.event.QMembershipRawEvent.*;

@Repository
@Transactional
public class MembershipEventStoreRepository implements EventRepository<MembershipId, MembershipRawEvent> {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public long countByIdentifier(MembershipId identifier) {
        return jpaQueryFactory.select(membershipRawEvent)
                .from(membershipRawEvent)
                .where(eqIdentifier(identifier)).fetchCount();
    }

    @Override
    public List<MembershipRawEvent> findByIdentifier(MembershipId identifier) {
        return jpaQueryFactory.select(membershipRawEvent)
                .from(membershipRawEvent)
                .where(eqIdentifier(identifier)).fetch();
    }

    @Override
    public List<MembershipRawEvent> findAfterVersionEventByIdentifier(MembershipId identifier, Long version) {
        return jpaQueryFactory.select(membershipRawEvent)
                .from(membershipRawEvent)
                .where(eqIdentifier(identifier), membershipRawEvent.version.gt(version)).fetch();
    }

    private BooleanExpression eqIdentifier(MembershipId identifier){
        return membershipRawEvent.identifiier.eq(identifier.get().toString());
    }

    @Override
    public void save(MembershipRawEvent event) {
        entityManager.persist(event);
    }
}
