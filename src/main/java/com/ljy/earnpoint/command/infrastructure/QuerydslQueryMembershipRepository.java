package com.ljy.earnpoint.command.infrastructure;

import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.MembershipRepository;
import com.ljy.earnpoint.command.domain.UserId;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.ljy.earnpoint.query.model.QMembership.membership;

@Repository
public class QuerydslQueryMembershipRepository implements MembershipRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean existByMembershipTypeAndUserId(Class<? extends Membership> type, UserId userId) {
        Integer integer = jpaQueryFactory.selectOne()
                .from(membership)
                .where(eqType(type), eqUserId(userId))
                .fetchFirst();
        return integer != null && integer > 0;
    }

    private BooleanExpression eqType(Class<? extends Membership> type) {
        return membership.type.eq(type.getSimpleName());
    }

    private BooleanExpression eqUserId(UserId userId) {
        return membership.userId.eq(userId.get());
    }
}
