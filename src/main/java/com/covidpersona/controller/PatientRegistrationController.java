package com.covidpersona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.dto.RegisterRequestDto;
import com.covidpersona.service.PatientService;
import com.covidpersona.service.auth.PersonaUserService;

@RestController
@RequestMapping("/patient")
public class PatientRegistrationController {

	@Autowired
	private PersonaUserService personaUserService;

	@PostMapping
	public long registerUserAccount(@ModelAttribute("user") RegisterRequestDto registrationDto) {
		return personaUserService.RegisterPersonaUser(registrationDto.getPersonaUser(), registrationDto.getPerson());
	}

}
