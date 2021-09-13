package com.ljy.earnpoint.domain;

import com.ljy.earnpoint.domain.values.Point;
import com.ljy.earnpoint.domain.values.UserId;
import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 신세계 멤버십
 */
@Entity
@DiscriminatorValue("Shinsegae")
public class Shinsegae extends Membership {

    protected Shinsegae(){}

    @Builder
    private Shinsegae(Point point, UserId userId){
        super(point, userId);
    }
}
