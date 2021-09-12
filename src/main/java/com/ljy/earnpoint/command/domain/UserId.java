package com.ljy.earnpoint.command.domain;

import java.util.Objects;

/**
 * 멤버십 사용자 아이디
 */
public class UserId {
    private final String id;

    protected UserId() { id = null;}

    private UserId(String id) {
        this.id = id;
    }

    public static UserId of(String id){
        return new UserId(id);
    }

    public String get() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(id, userId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
