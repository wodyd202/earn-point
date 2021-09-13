package com.ljy.earnpoint.domain.values;

import com.ljy.earnpoint.domain.exception.InvalidPointException;

import java.io.Serializable;
import java.util.Objects;

/**
 * 멤버십 포인트
 */
public class Point implements Serializable {
    private final long won;

    protected Point() {won = 0;}

    private Point(long won) {
        if(won < 0){
            throw new InvalidPointException();
        }
        this.won = won;
    }

    public static Point won(long won){
        return new Point(won);
    }

    public long get() {
        return won;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return won == point.won;
    }

    @Override
    public int hashCode() {
        return Objects.hash(won);
    }
}
