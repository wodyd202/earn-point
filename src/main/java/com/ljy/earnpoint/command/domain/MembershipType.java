package com.ljy.earnpoint.command.domain;

public enum MembershipType {
    HAPPY_POINT{
        @Override
        public Membership create(long point, UserId userid) {
            return HappyPoint.builder()
                    .userId(userid)
                    .point(Point.won(point))
                    .build();
        }
    };

    public abstract Membership create(long point, UserId userid);
}
