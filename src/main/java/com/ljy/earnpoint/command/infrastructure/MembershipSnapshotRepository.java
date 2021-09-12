package com.ljy.earnpoint.command.infrastructure;

import com.ljy.core.es.snapshot.Snapshot;
import com.ljy.core.es.snapshot.SnapshotRepository;
import com.ljy.earnpoint.command.domain.Membership;
import com.ljy.earnpoint.command.domain.MembershipId;
import com.ljy.earnpoint.command.domain.es.MembershipSnapshot;
import com.ljy.earnpoint.command.domain.es.QMembershipSnapshot;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional
public class MembershipSnapshotRepository implements SnapshotRepository<Membership, MembershipId> {
    @Autowired private JPAQueryFactory jpaQueryFactory;
    @PersistenceContext private EntityManager entityManager;

    @Override
    public Optional<Snapshot<Membership, MembershipId>> findLatest(MembershipId identifier) {
        MembershipSnapshot membershipSnapshot = jpaQueryFactory.selectFrom(QMembershipSnapshot.membershipSnapshot).where(QMembershipSnapshot.membershipSnapshot.identifier().eq(identifier)).fetchFirst();
        return Optional.ofNullable(membershipSnapshot);
    }

    @Override
    public <T extends Snapshot<Membership, MembershipId>> void save(T snapshot) {
        entityManager.persist(snapshot);
    }

}
