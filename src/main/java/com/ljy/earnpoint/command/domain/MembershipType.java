package com.ljy.earnpoint.command.domain;

/**
 * 멤버십 타입
 */
public enum MembershipType {
    HAPPY_POINT{
        @Override
        public Membership create(long point, UserId userid) {
            return HappyPoint.builder()
                    .userId(userid)
                    .point(Point.won(point))
                    .build();
        }
    },
    CJ_ONE {
        @Override
        public Membership create(long point, UserId userid) {
            return CjOne.builder()
                    .userId(userid)
                    .point(Point.won(point))
                    .build();
        }
    },
    SHINSEGAE{
        @Override
        public Membership create(long point, UserId userid) {
            return Shinsegae.builder()
                    .userId(userid)
                    .point(Point.won(point))
                    .build();
        }
    };

    public abstract Membership create(long point, UserId userid);
}
