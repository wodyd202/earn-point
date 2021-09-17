package com.ljy.earnpoint.command.presentation;

import com.ljy.earnpoint.command.application.AbleMembershipService;
import com.ljy.earnpoint.command.application.EnableMembershipService;
import com.ljy.earnpoint.core.http.APIResponse;
import com.ljy.earnpoint.core.http.CommandException;
import com.ljy.earnpoint.command.application.RegisterMembershipService;
import com.ljy.earnpoint.domain.RegisterMembership;
import com.ljy.earnpoint.domain.exception.*;
import com.ljy.earnpoint.domain.read.MembershipModel;
import com.ljy.earnpoint.domain.values.MembershipId;
import com.ljy.earnpoint.domain.values.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/membership")
public class MembershipAPI {
    @Autowired private RegisterMembershipService registerMembershipService;
    @Autowired private EnableMembershipService enableMembershipService;
    @Autowired private AbleMembershipService ableMembershipService;

    @PostMapping
    public APIResponse register(@Valid @RequestBody RegisterMembership registerMembership,
                                Errors errors,
                                @RequestHeader("USER-ID") String userId){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        MembershipModel membershipModel = registerMembershipService.register(registerMembership, UserId.of(userId));
        return new APIResponse(membershipModel, HttpStatus.OK);
    }

    @DeleteMapping("{membershipId}")
    public APIResponse enable(@PathVariable MembershipId membershipId, @RequestHeader("USER-ID") String userId){
        enableMembershipService.enable(membershipId, UserId.of(userId));
        return new APIResponse(null, HttpStatus.OK);
    }

    @PutMapping("{membershipId}/active")
    public APIResponse able(@PathVariable MembershipId membershipId, @RequestHeader("USER-ID") String userId){
        MembershipModel membershipModel = ableMembershipService.able(membershipId, UserId.of(userId));
        return new APIResponse(membershipModel, HttpStatus.OK);
    }

    @ExceptionHandler({
            AlreadyExistMembershipException.class,
            InvalidPointException.class,
            MembershipNotFoundException.class
    })
    public APIResponse error(IllegalArgumentException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            AlreadyEnabledMembershipException.class,
            AlreadyAbledMembershipException.class
    })
    public APIResponse error(IllegalStateException e){
        return new APIResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
