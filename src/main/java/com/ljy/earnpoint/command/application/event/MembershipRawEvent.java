package com.ljy.earnpoint.command.application.event;

import com.ljy.core.es.event.RawEvent;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
public class MembershipRawEvent implements RawEvent<String> {
    private String identifiier;
    private String type;
    private Long version;
    private String payload;
    private LocalDateTime createDateTime;

    @Override
    public String getIdentifier() {
        return identifiier;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public String getPayload() {
        return payload;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembershipRawEvent that = (MembershipRawEvent) o;
        return Objects.equals(identifiier, that.identifiier) && Objects.equals(type, that.type) && Objects.equals(version, that.version) && Objects.equals(payload, that.payload) && Objects.equals(createDateTime, that.createDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiier, type, version, payload, createDateTime);
    }
}
