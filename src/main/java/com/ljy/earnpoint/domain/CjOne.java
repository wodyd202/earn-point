package com.ljy.earnpoint.domain;

import com.ljy.earnpoint.domain.values.Point;
import com.ljy.earnpoint.domain.values.UserId;
import lombok.Builder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * cjone 멤버십
 */
@Entity
@DiscriminatorValue("CjOne")
public class CjOne extends Membership {

    protected CjOne(){}

    @Builder
    protected CjOne(Point point, UserId userId){
        super(point, userId);
    }
}
