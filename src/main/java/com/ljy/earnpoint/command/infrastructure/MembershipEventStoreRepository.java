package com.ljy.earnpoint.command.infrastructure;

import com.ljy.core.es.event.EventRepository;
import com.ljy.earnpoint.command.application.event.MembershipRawEvent;
import com.ljy.earnpoint.command.domain.MembershipId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MembershipEventStoreRepository implements EventRepository<MembershipId, MembershipRawEvent> {
    @Autowired private RedisTemplate<String,MembershipRawEvent> redisTemplate;
    @Value("${membership.event.key}")
    private String MEMBER_EVENT_KEY;

    @Override
    public long countByIdentifier(MembershipId identifier) {
        ListOperations<String, MembershipRawEvent> listOperations = redisTemplate.opsForList();
        return listOperations.size(MEMBER_EVENT_KEY + ":" + identifier.get());
    }

    @Override
    public List<MembershipRawEvent> findByIdentifier(MembershipId identifier) {
        ListOperations<String, MembershipRawEvent> listOperations = redisTemplate.opsForList();
        return listOperations.range(MEMBER_EVENT_KEY + ":" + identifier.get(), 0, -1);
    }

    @Override
    public List<MembershipRawEvent> findAfterVersionEventByIdentifier(MembershipId identifier, Long version) {
        ListOperations<String, MembershipRawEvent> listOperations = redisTemplate.opsForList();
        return listOperations.range(MEMBER_EVENT_KEY + ":" + identifier.get(), version, -1);
    }

    @Override
    public void save(MembershipRawEvent event) {
        ListOperations<String, MembershipRawEvent> listOperations = redisTemplate.opsForList();
        listOperations.rightPush(MEMBER_EVENT_KEY + ":" + event.getIdentifier(), event);
    }

}
