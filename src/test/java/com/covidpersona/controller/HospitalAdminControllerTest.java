package com.covidpersona.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.covidpersona.config.auth.JwtAuthenticationEntryPoint;
import com.covidpersona.config.auth.JwtUtil;
import com.covidpersona.entity.HospitalAdmin;
import com.covidpersona.service.HospitalAdminService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.covidpersona.service.auth.PersonaUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(HospitalAdminController.class)
@WithMockUser(username = "test", password = "test", roles = "ADMIN")
public class HospitalAdminControllerTest {

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
	private HospitalAdminService hospitalAdminService;
	
	@Autowired
    ObjectMapper mapper;
	
	final String URL="/hospitalAdmins";
	
	@Test
	public void createHospitalAdmin_test() throws Exception {
		HospitalAdmin hospitalAdmin = new HospitalAdmin();
		hospitalAdmin.setName("Test Name");
		
		given(hospitalAdminService.addHospAdmin(hospitalAdmin)).willReturn(hospitalAdmin);
		
		mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsBytes(hospitalAdmin)))
                .andExpect(status().isOk());
	}
	

	@Test
    public void getHospitalAdminById_test() throws Exception {

		Optional<HospitalAdmin> hospitalAdmin = Optional.of(new HospitalAdmin());
		hospitalAdmin.get().setName("Test Name");
		hospitalAdmin.get().setId(2L);

        given(hospitalAdminService.getHospAdmin(hospitalAdmin.get().getId())).willReturn(hospitalAdmin);

        mockMvc.perform(get(URL + Long.toString(hospitalAdmin.get().getId()))
                .contentType(MediaType.APPLICATION_JSON));
            //    .andExpect(status().isOk())
            //    .andExpect(jsonPath("name", is(hospitalAdmin.get().getName())));

    }
	
	@Test
    public void sendNotFoundStatus_whenManagerDoestExist() throws Exception {
		Optional<HospitalAdmin> hospitalAdmin = Optional.of(new HospitalAdmin());
		hospitalAdmin.get().setName("Test Name");
		hospitalAdmin.get().setId(2L);

        given(hospitalAdminService.getHospAdmin(hospitalAdmin.get().getId())).willReturn(Optional.empty());

        mockMvc.perform(get(URL + Long.toString(hospitalAdmin.get().getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
	
	@Test
    public void listAllHospitalAdmins_test() throws Exception {

		HospitalAdmin hospitalAdmin = new HospitalAdmin();
		hospitalAdmin.setName("Test name");

        List<HospitalAdmin> hospitalAdmins = new ArrayList<>();
        hospitalAdmins.add(hospitalAdmin);

        given(hospitalAdminService.getHospAdmins()).willReturn(hospitalAdmins);

        mockMvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON))
        		.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(hospitalAdmin.getName())));
    }
	
//	@Test
//    public void removeHospitalAdminById_test() throws Exception {
//		HospitalAdmin hospitalAdmin = new HospitalAdmin();
//		hospitalAdmin.setName("Test Name");
//		hospitalAdmin.setId(2L);
//
//        doNothing().when(hospitalAdminService).deleteHospAdmin(hospitalAdmin.getId());
//
//        mockMvc.perform(delete(URL + Long.toString(hospitalAdmin.getId()))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
	
}

