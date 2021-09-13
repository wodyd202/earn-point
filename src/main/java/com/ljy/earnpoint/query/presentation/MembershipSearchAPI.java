package com.ljy.earnpoint.query.presentation;

import com.ljy.earnpoint.core.http.APIResponse;
import com.ljy.earnpoint.domain.read.MembershipModel;
import com.ljy.earnpoint.query.application.MembershipSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/membership")
public class MembershipSearchAPI {
    @Autowired private MembershipSearchService membershipSearchService;

    @GetMapping
    public APIResponse findByUserId(@RequestHeader("USER-ID") String userId){
        List<MembershipModel> membershipModels = membershipSearchService.findByUserId(userId);
        return new APIResponse(membershipModels, HttpStatus.OK);
    }
}
