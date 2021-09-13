package com.ljy.earnpoint.query.infrastructure;

import com.ljy.earnpoint.domain.read.MembershipModel;
import com.ljy.earnpoint.domain.values.MembershipState;
import com.ljy.earnpoint.domain.values.UserId;
import com.ljy.earnpoint.query.application.MembershipSearchRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ljy.earnpoint.domain.QMembership.membership;
import static com.querydsl.core.types.dsl.Expressions.asSimple;

@Repository
public class QuerydslMembershipSearchRepository implements MembershipSearchRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MembershipModel> findByUserId(String userId) {
        return jpaQueryFactory.select(Projections.constructor(MembershipModel.class,
                        membership.membershipId(),
                        membership.point,
                        asSimple(userId)))
                .from(membership).where(eqUserId(userId), membership.state.eq(MembershipState.ACTIVE)).fetch();
    }

    private BooleanExpression eqUserId(String userId){
        return membership.userId().eq(UserId.of(userId));
    }
}
