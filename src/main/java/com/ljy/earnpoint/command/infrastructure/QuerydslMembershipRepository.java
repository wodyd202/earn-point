package com.ljy.earnpoint.command.infrastructure;

import com.ljy.earnpoint.command.application.MembershipRepository;
import com.ljy.earnpoint.domain.Membership;
import com.ljy.earnpoint.domain.QMembership;
import com.ljy.earnpoint.domain.values.MembershipId;
import com.ljy.earnpoint.domain.values.UserId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

import static com.ljy.earnpoint.domain.QMembership.membership;

@Repository
@Transactional
public class QuerydslMembershipRepository implements MembershipRepository {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public Optional<Membership> findByMembershipIdAndUserId(MembershipId membershipId, UserId userId) {
        Membership membership = jpaQueryFactory.select(QMembership.membership)
                .from(QMembership.membership)
                .where(QMembership.membership.userId().eq(userId).and(QMembership.membership.membershipId().eq(membershipId)))
                .fetchFirst();
        return Optional.ofNullable(membership);
    }

    private String EXIST_BY_USERID_AND_TYPE_QUERY = "SELECT userid FROM membership WHERE userid = :id AND dtype = :type LIMIT 0,1";
    @Override
    public boolean existByUserIdAndType(UserId userId, String type) {
        Query nativeQuery = entityManager.createNativeQuery(EXIST_BY_USERID_AND_TYPE_QUERY);
        nativeQuery.setParameter("id", userId.get())
                .setParameter("type", type);
        try{
            nativeQuery.getSingleResult();
            return true;
        }catch (NoResultException e){
            return false;
        }
    }

    @Override
    public void save(Membership membership) {
        if(entityManager.contains(membership)){
            entityManager.merge(membership);
        }else{
            entityManager.persist(membership);
        }
    }

}
