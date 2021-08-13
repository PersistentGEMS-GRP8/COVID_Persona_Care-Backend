package com.covidpersona.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.entity.PersonaUser;
import com.covidpersona.entity.ReceptionistRegistrationDTO;
import com.covidpersona.service.ReceptionistService;
import com.covidpersona.service.auth.PersonaUserService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/receptionists")
public class ReceptionistRegistrationController {

	@Autowired
	private ReceptionistService receptionistService;

	@Autowired
	private PersonaUserService personaUserService;

	@Transactional
	@PostMapping("/register")
	public ResponseEntity<?> registerManager(@RequestBody ReceptionistRegistrationDTO receptionistRegDTO) {
		System.out.println(receptionistRegDTO);
		receptionistService.addReceptionist(receptionistRegDTO.getReceptionist());
		PersonaUser personaUser = receptionistRegDTO.getPersonaUser();
//		personaUserService.RegisterPersonaUser(personaUser.getUsername(),personaUser.getPassword(),personaUser.getRole());
		return ResponseEntity.ok().build();
	}
}
