package com.ljy.earnpoint.command.domain;

import com.ljy.earnpoint.command.domain.exception.AlreadyExistMembershipException;
import org.springframework.stereotype.Component;

/**
 * 멤버십 등록시 사용되는 validator
 */
@Component
public class RegisterMembershipValidator {
    private final MembershipRepository membershipRepository;

    public RegisterMembershipValidator(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    /**
     * @param membership 멤버십 클래스 타입
     * @param userId 멤버십 생성자 아이디
     */
    public void validation(Class<? extends Membership> membership, UserId userId) {
        // 이미 해당 멤버십을 갖고 있는지 확인
        if(existMembership(membership, userId)){
            throw new AlreadyExistMembershipException("already exist membership type");
        }
    }

    private boolean existMembership(Class<? extends Membership> membership, UserId userId) {
        return membershipRepository.existByMembershipTypeAndUserId(membership, userId);
    }
}
