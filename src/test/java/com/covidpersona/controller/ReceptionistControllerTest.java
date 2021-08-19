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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

import com.covidpersona.config.auth.JwtAuthenticationEntryPoint;
import com.covidpersona.config.auth.JwtUtil;
import com.covidpersona.entity.Receptionist;
import com.covidpersona.service.ReceptionistService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.covidpersona.service.auth.PersonaUserService;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@WebMvcTest(ReceptionistController.class)
public class ReceptionistControllerTest {

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
	private ReceptionistService receptionistService;
	
	@Autowired
    ObjectMapper mapper;
	
	@Test
	public void createReceptionist_whenPostMethod() throws Exception {
		Receptionist receptionist = new Receptionist();
		receptionist.setName("Test Name");
		
		given(receptionistService.addReceptionist(receptionist)).willReturn(receptionist);
		
		mockMvc.perform(post("/receptionists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsBytes(receptionist)))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.name", is(receptionist.getName())));
	}
	
	@Test
    public void removeReceptionistById_whenDeleteMethod() throws Exception {
		Receptionist receptionist = new Receptionist();
		receptionist.setName("Test Name");
		receptionist.setId(22L);

        doNothing().when(receptionistService).deleteReceptionist(receptionist.getId());

        mockMvc.perform(delete("/receptionists/" + Long.toString(receptionist.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
	@Test
    public void getReceptionistById_whenGetMethod() throws Exception {

		Optional<Receptionist> receptionist = Optional.of(new Receptionist());
		receptionist.get().setName("Test Name");
		receptionist.get().setId(22L);

        given(receptionistService.getReceptionist(receptionist.get().getId())).willReturn(receptionist);

        mockMvc.perform(get("/receptionists/" + Long.toString(receptionist.get().getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("name", is(receptionist.get().getName())));
    }
	
//	@Test
//    public void sendNotFoundStatus_whenReceptionistDoestExist() throws Exception {
//		Optional<Receptionist> receptionist = Optional.of(new Receptionist());
//		receptionist.get().setName("Test Name");
//		receptionist.get().setId(22L);
//
//        given(receptionistService.getReceptionist(receptionist.get().getId())).willReturn(Optional.empty());
//
//        mockMvc.perform(get("/receptionists/" + Long.toString(receptionist.get().getId()))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
	
	@Test
    public void listAllReceptionists_whenGetMethod() throws Exception {

		Receptionist receptionist = new Receptionist();
		receptionist.setName("Test name");

        List<Receptionist> receptionists = new ArrayList<>();
        receptionists.add(receptionist);

        given(receptionistService.getReceptionists()).willReturn(receptionists);

        mockMvc.perform(get("/receptionists")
                .contentType(MediaType.APPLICATION_JSON))
        		.andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name", is(receptionist.getName())));
    }
	
	@Test
    public void listReceptionists_whenGetByHIdMethod() throws Exception {

		Receptionist receptionist = new Receptionist();
		receptionist.setName("Test name");
		receptionist.sethId(2);

        List<Receptionist> receptionists = new ArrayList<>();
        receptionists.add(receptionist);

        given(receptionistService.getReceptionistsByHId(receptionist.gethId())).willReturn(receptionists);

        mockMvc.perform(get("/receptionists/getByHId/"+ Long.toString(receptionist.gethId()))
                .contentType(MediaType.APPLICATION_JSON))
        		.andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$[0].name", is(receptionist.getName())));
    }
}
