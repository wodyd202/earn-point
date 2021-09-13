package com.ljy.earnpoint.domain;

import com.ljy.earnpoint.domain.values.Point;
import com.ljy.earnpoint.domain.values.UserId;
import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 해피포인트 멤버십
 */
@Entity
@DiscriminatorValue("HappyPoint")
public class HappyPoint extends Membership {

    protected HappyPoint(){}

    @Builder
    private HappyPoint(Point point, UserId userId){
        super(point, userId);
    }
}
