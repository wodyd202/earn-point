package com.ljy.earnpoint.command.presentation;

import com.ljy.earnpoint.core.http.APIResponse;
import com.ljy.earnpoint.core.http.CommandException;
import com.ljy.earnpoint.command.application.RegisterMembershipService;
import com.ljy.earnpoint.command.application.model.RegisterMembership;
import com.ljy.earnpoint.domain.read.MembershipModel;
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

}
