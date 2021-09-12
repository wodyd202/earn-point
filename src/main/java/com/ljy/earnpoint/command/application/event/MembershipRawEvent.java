package com.ljy.earnpoint.command.application.event;

import com.ljy.core.es.event.RawEvent;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * 멤버십 이벤트
 */
@Entity
@Table(name = "membership_events",
        indexes = {@Index(columnList = "identifiier")})
public class MembershipRawEvent implements RawEvent<String> {

    @Id
    private final UUID id;
    private final String identifiier;
    private final String type;
    private final Long version;
    private final String payload;
    private final LocalDateTime createDateTime;

    public UUID getId() {
        return id;
    }

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

    protected MembershipRawEvent(){
        this.identifiier = null;
        this.type = null;
        this.version = null;
        this.payload = null;
        this.createDateTime = null;
        id = null;
    }

    /**
     * @param identifiier 멤버십 고유 번호
     * @param type 이벤트 타입
     * @param version 이벤트 버전
     * @param payload 데이터
     * @param createDateTime 이벤트 발행일
     */
    @Builder
    public MembershipRawEvent(String identifiier, String type, Long version, String payload, LocalDateTime createDateTime) {
        this.identifiier = identifiier;
        this.type = type;
        this.version = version;
        this.payload = payload;
        this.createDateTime = createDateTime;
        id = UUID.randomUUID();
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
