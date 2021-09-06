package com.ljy.earnpoint.query.infrastructure;

import com.ljy.earnpoint.query.model.Membership;
import com.ljy.earnpoint.query.model.MembershipRepository;
import com.ljy.earnpoint.query.model.QMembership;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.ljy.earnpoint.query.model.QMembership.membership;

@Repository
@Transactional
public class QuerydslMembershipRepository implements MembershipRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public Membership findByMembershipId(String membershipId) {
        return jpaQueryFactory.selectFrom(membership)
                .where(membership.membershipId.eq(membershipId))
                .fetchFirst();
    }

    @Override
    public void save(Membership membership) {
        entityManager.persist(membership);
    }

    @Override
    public List<Membership> findByUserId(String userId) {
        return jpaQueryFactory.selectFrom(membership)
                              .where(membership.userId.eq(userId))
                              .fetch();
    }
}
