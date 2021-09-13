package com.ljy.earnpoint.command.infrastructure;

import com.ljy.earnpoint.command.application.MembershipRepository;
import com.ljy.earnpoint.domain.Membership;
import com.ljy.earnpoint.domain.values.UserId;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class QuerydslMembershipRepository implements MembershipRepository {
    @PersistenceContext private EntityManager entityManager;

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
