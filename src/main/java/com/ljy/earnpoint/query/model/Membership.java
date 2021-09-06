package com.ljy.earnpoint.query.model;

import com.ljy.earnpoint.command.application.event.RegisteredMembershipEvent;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "membership", indexes = {
        @Index(columnList = "userId")
})
public class Membership {
    @Id
    private final String membershipId;
    private long point;
    private String type;
    private String state;
    private LocalDateTime createDateTime;
    private String userId;

    protected Membership(){
        membershipId = null;
    }

    public Membership(RegisteredMembershipEvent event){
        membershipId = event.getMembershipId().get().toString();
        point = event.getPoint().get();
        state = event.getState().toString();
        createDateTime = event.getCreateDateTime();
        userId = event.getUserId().get();
        type = event.getType().getSimpleName();
    }
}
