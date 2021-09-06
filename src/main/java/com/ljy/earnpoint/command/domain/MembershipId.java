package com.ljy.earnpoint.command.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * 멤버십 고유 번호
 */
@Embeddable
public class MembershipId implements Serializable {
    private final UUID id;

    protected MembershipId(){id = null;}

    private MembershipId(UUID id) {
        this.id = id;
    }

    public static MembershipId newInstance(){
        return new MembershipId(UUID.randomUUID());
    }

    public UUID get() {
        return id;
    }

    @Override
    public String toString() {
        return "MembershipId{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembershipId that = (MembershipId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
