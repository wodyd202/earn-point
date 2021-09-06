package com.ljy.earnpoint.command.domain;

import com.ljy.earnpoint.command.domain.exception.InvalidPointException;

import java.util.Objects;

public class Point {
    private final long won;

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
