package com.covidpersona.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.dto.RegisterRequestDto;
import com.covidpersona.entity.Doctor;
import com.covidpersona.entity.Manager;
import com.covidpersona.entity.ManagerRegistrationDTO;
import com.covidpersona.entity.PersonaUser;
import com.covidpersona.entity.Specialization;
import com.covidpersona.service.ManagerService;
import com.covidpersona.service.auth.PersonaUserService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/managers")
public class ManagerRegistrationController {

//	@Autowired
//	private ManagerService managerService;
//	
	@Autowired
	private PersonaUserService personaUserService;
	
//	@Transactional
//	@PostMapping("/register")
//	public ResponseEntity<?> registerManager(@RequestBody ManagerRegistrationDTO managerRegDTO){
//		System.out.println(managerRegDTO);
////		managerService.addManager(managerRegDTO.getManager());
//		PersonaUser personaUser = managerRegDTO.getPersonaUser();
//		personaUserService.RegisterPersonaUser(managerRegDTO.getPersonaUser(), managerRegDTO.getManager());
//		return ResponseEntity.ok().build();
//	}
	
	@PostMapping("/register")
	public long registerManager(@RequestBody RegisterRequestDto dto) {
		return personaUserService.RegisterPersonaUser(dto.getPersonaUser(), dto.getPerson());
	}
}
