package com.covidpersona.controller;

import com.covidpersona.config.auth.JwtAuthenticationEntryPoint;
import com.covidpersona.config.auth.JwtUtil;
import com.covidpersona.model.auth.AuthenticationRequest;
import com.covidpersona.service.ProfileService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.covidpersona.service.auth.PersonaUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationController.class)
public class AthenticationControllerTest {

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private PersonaUserService personaUserService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private ProfileService profileService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void authenticationTest() throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("Admin");
        authenticationRequest.setPassword("admin");
        mockMvc.perform(post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsBytes(authenticationRequest)))
                .andExpect(status().isOk());
    }
}
