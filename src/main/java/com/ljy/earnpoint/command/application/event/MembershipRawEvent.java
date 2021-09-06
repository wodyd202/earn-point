package com.ljy.earnpoint.command.application.event;

import com.ljy.core.es.event.RawEvent;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 멤버십 이벤트
 */
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
