package com.ljy.earnpoint.domain.exception;

public class AlreadyExistMembershipException extends IllegalArgumentException {
    public AlreadyExistMembershipException(String msg) {
        super(msg);
    }
}
