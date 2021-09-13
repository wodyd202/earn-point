package com.ljy.earnpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.earnpoint.command.application.model.RegisterMembership;
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
}
