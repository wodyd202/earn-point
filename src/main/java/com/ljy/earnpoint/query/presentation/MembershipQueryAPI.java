package com.ljy.earnpoint.query.presentation;

import com.ljy.core.http.APIResponse;
import com.ljy.earnpoint.query.application.MembershipSearchService;
import com.ljy.earnpoint.query.model.Membership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/membership")
public class MembershipQueryAPI {
    @Autowired private MembershipSearchService membershipSearchService;

    @GetMapping
    public APIResponse getAllMembership(@RequestHeader("USER-ID") String userId){
        List<Membership> allMembership = membershipSearchService.getAllMembership(userId);
        return new APIResponse(allMembership, HttpStatus.OK);
    }
}
