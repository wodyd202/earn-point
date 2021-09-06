package com.ljy.earnpoint.command.domain.exception;

public class AlreadyExistMembershipException extends IllegalArgumentException {
    public AlreadyExistMembershipException(String msg) {
        super(msg);
    }
}
