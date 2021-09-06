package com.ljy.earnpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.earnpoint.command.application.RegisterMembershipService;
import com.ljy.earnpoint.command.application.model.RegisterMembership;
import com.ljy.earnpoint.command.domain.UserId;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.ljy.earnpoint.Fixture.aRegisterMembership;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MembershipAPI_Test {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @Test
    void invalidPoint() throws Exception {
        RegisterMembership registerMembership = aRegisterMembership().point(-1).build();
        assertRegisterMembershipAPI(registerMembership, status().isBadRequest());
    }

    @Test
    void nullType() throws Exception {
        RegisterMembership registerMembership = aRegisterMembership().type(null).build();
        assertRegisterMembershipAPI(registerMembership, status().isBadRequest());
    }

    @Test
    void register() throws Exception {
        RegisterMembership registerMembership = aRegisterMembership().build();
        assertRegisterMembershipAPI(registerMembership, status().isOk());
    }

    @Autowired RegisterMembershipService registerMembershipService;
    @Test
    void getAllMembership() throws Exception {
        RegisterMembership registerMembership = aRegisterMembership().build();
        registerMembershipService.register(registerMembership, UserId.of("getAllMembership"));
        mockMvc.perform(get("/api/membership")
                    .header("USER-ID","getAllMembership"))
            .andExpect(status().isOk());
    }

    private void assertRegisterMembershipAPI(RegisterMembership registerMembership, ResultMatcher resultMatcher) throws Exception{
        mockMvc.perform(post("/api/membership")
                        .header("USER-ID","userid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerMembership)))
                .andExpect(resultMatcher);
    }
}
