package com.ljy.earnpoint.command.presentation;

import com.ljy.core.es.command.CommandException;
import com.ljy.core.http.APIResponse;
import com.ljy.earnpoint.command.application.RegisterMembershipService;
import com.ljy.earnpoint.command.application.model.RegisterMembership;
import com.ljy.earnpoint.command.application.model.MembershipModel;
import com.ljy.earnpoint.command.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/membership")
public class MembershipCommandAPI {
    @Autowired private RegisterMembershipService registerMembershipService;

    @PostMapping
    public APIResponse register(@Valid @RequestBody RegisterMembership registerMembership,
                                Errors errors,
                                @RequestHeader("USER-ID") String userId){
        if(errors.hasErrors()){
            throw new CommandException(errors);
        }
        MembershipModel memberModel = registerMembershipService.register(registerMembership, UserId.of(userId));
        return new APIResponse(memberModel, HttpStatus.OK);
    }

    @ExceptionHandler(CommandException.class)
    public APIResponse error(CommandException e){
        return new APIResponse(e.getMessages(), HttpStatus.BAD_REQUEST);
    }
}
