package com.ljy.earnpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.earnpoint.command.application.EnableMembershipService;
import com.ljy.earnpoint.command.application.RegisterMembershipService;
import com.ljy.earnpoint.domain.RegisterMembership;
import com.ljy.earnpoint.domain.read.MembershipModel;
import com.ljy.earnpoint.domain.values.MembershipId;
import com.ljy.earnpoint.domain.values.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.ljy.earnpoint.Fixture.aRegisterMembership;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MembershipAPI_Test {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RegisterMembershipService registerMembershipService;

    @Test
    @DisplayName("멤버십 생성 요청시 타입은 반드시 입력해야함")
    void regsiter_emptyType() throws Exception {
        RegisterMembership registerMembership = aRegisterMembership().type(null).build();
        assertRegister(registerMembership, status().isBadRequest());
    }

    @Test
    @DisplayName("멤버십 등록")
    void register() throws Exception {
        RegisterMembership registerMembership = aRegisterMembership().build();
        assertRegister(registerMembership, status().isOk());
    }

    private void assertRegister(RegisterMembership registerMembership, ResultMatcher resultMatcher) throws Exception{
        mockMvc.perform(post("/api/membership")
                        .header("USER-ID", "userId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerMembership)))
                .andExpect(resultMatcher);
    }

    @Test
    @DisplayName("자신의 멤버십 조회")
    void findByUserId() throws Exception {
        mockMvc.perform(get("/api/membership")
                .header("USER-ID","userId"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("멤버십 비활성화")
    void enable() throws Exception {
        RegisterMembership registerMembership = aRegisterMembership().build();
        MembershipModel membershipModel = registerMembershipService.register(registerMembership, UserId.of("userId"));
        mockMvc.perform(delete("/api/membership/{membershipId}",membershipModel.getMembershipId())
                .header("USER-ID","userId"))
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("해당 멤버십 아이디가 존재하지 않으면 안됨")
    void enable_notFound() throws Exception {
        mockMvc.perform(delete("/api/membership/{membershipId}","notFound")
                        .header("USER-ID","userId"))
                .andExpect(status().isBadRequest());
    }

    @Autowired
    EnableMembershipService enableMembershipService;

    @Test
    @DisplayName("멤버십 활성화")
    void able() throws Exception {
        RegisterMembership registerMembership = aRegisterMembership().build();
        MembershipModel membershipModel = registerMembershipService.register(registerMembership, UserId.of("ableMembership"));
        enableMembershipService.enable(MembershipId.of(membershipModel.getMembershipId()), UserId.of("ableMembership"));

        mockMvc.perform(put("/api/membership/{membershipId}/active", membershipModel.getMembershipId())
                .header("USER-ID","ableMembership"))
            .andExpect(status().isOk());
    }

}
