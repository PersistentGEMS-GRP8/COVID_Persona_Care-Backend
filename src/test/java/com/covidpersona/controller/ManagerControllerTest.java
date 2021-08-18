package com.covidpersona.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

import com.covidpersona.config.auth.JwtAuthenticationEntryPoint;
import com.covidpersona.config.auth.JwtUtil;
import com.covidpersona.entity.Manager;
import com.covidpersona.service.ManagerService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.covidpersona.service.auth.PersonaUserService;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@WebMvcTest(ManagerController.class)
public class ManagerControllerTest {

	@MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private PersonaUserService personaUserService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ManagerService managerService;
	
	@Autowired
    ObjectMapper mapper;
	
	@Test
	public void createManager_whenPostMethod() throws Exception {
		Manager manager = new Manager();
		manager.setName("Test Name");
		
		given(managerService.addManager(manager)).willReturn(manager);
		
		mockMvc.perform(post("/managers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsBytes(manager)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(manager.getName())));
	}
	
	@Test
    public void removeUserById_whenDeleteMethod() throws Exception {
		Manager manager = new Manager();
		manager.setName("Test Name");
		manager.setId(22L);

        doNothing().when(managerService).deleteManager(manager.getId());

        mockMvc.perform(delete("/managers/" + Long.toString(manager.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
	
	@Test
    public void getManagerById_whenGetMethod() throws Exception {

		Optional<Manager> manager = Optional.of(new Manager());
		manager.get().setName("Test Name");
		manager.get().setId(22L);

        given(managerService.getManager(manager.get().getId())).willReturn(manager);

        mockMvc.perform(get("/managers/" + Long.toString(manager.get().getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(manager.get().getName())));
    }
	
	@Test
    public void sendNotFoundStatus_whenManagerDoestExist() throws Exception {
		Optional<Manager> manager = Optional.of(new Manager());
		manager.get().setName("Test Name");
		manager.get().setId(22L);

        given(managerService.getManager(manager.get().getId())).willReturn(null);

        mockMvc.perform(get("/managers/" + Long.toString(manager.get().getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
	
//	@Test
//    public void listAllManagers_whenGetMethod() throws Exception {
//
//		Manager manager = new Manager();
//		manager.setName("Test name");
//
//        List<Manager> allManagers = Arrays.asList(manager);
//
//        given(managerService.getManagers()).willReturn(allManagers);
//
//        mockMvc.perform(get("/managers")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(manager.getName())));
//    }
}
