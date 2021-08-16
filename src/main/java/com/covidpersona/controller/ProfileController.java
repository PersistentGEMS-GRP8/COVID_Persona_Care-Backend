package com.covidpersona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.config.auth.JwtUtil;
import com.covidpersona.entity.Person;
import com.covidpersona.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private ProfileService profileService;

	@GetMapping
	public Person getPersonInfo() {
		System.out.println(jwtUtil.getUserId() + " "+ jwtUtil.getRoles().get(0).toString());
		return profileService.getPersonData(jwtUtil.getUserId(), jwtUtil.getRoles().get(0).toString());
	}
}
