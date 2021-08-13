package com.covidpersona.controller;

import com.covidpersona.config.Roles;
import com.covidpersona.config.auth.JwtUtil;
import com.covidpersona.dto.RegisterRequestDto;
import com.covidpersona.entity.Person;
import com.covidpersona.entity.PersonaUser;
import com.covidpersona.exception.InvalidDataException;
import com.covidpersona.model.auth.AuthenticationRequest;
import com.covidpersona.model.auth.AuthenticationResponce;
import com.covidpersona.service.AdminService;
import com.covidpersona.service.DoctorService;
import com.covidpersona.service.HospitalAdminService;
import com.covidpersona.service.ManagerService;
import com.covidpersona.service.ReceptionistService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.covidpersona.service.auth.PersonaUserService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PersonaUserService personaUserService;

	private final AdminService adminService;
	private final HospitalAdminService hospitalAdminService;
	private final ManagerService managerService;
	private final ReceptionistService receptionistService;
	private final DoctorService doctorService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		PersonaUser user = customUserDetailsService.getUser();
		System.out.println(user);
		String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponce(token, getPersonData(user.getId(), user.getRole())));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public long createUser(@RequestBody RegisterRequestDto registerRequestDto) throws InvalidDataException {
		long id = personaUserService.RegisterPersonaUser(registerRequestDto.getPersonaUser(),
				registerRequestDto.getPerson());

		return id;
	}

	private Person getPersonData(long id, String role) {
		if (role.equals(Roles.ROLE_ADMIN.toString()))
			return adminService.getPersonByUserId(id);

		if (role.equals(Roles.ROLE_HOSPITALADMIN.toString())) {
			return hospitalAdminService.getPersonByUserId(id);
		}

		if (role.equals(Roles.ROLE_MANAGER.toString()))
			return managerService.getPersonByUserId(id);

		if (role.equals(Roles.ROLE_RECEPTIONIST.toString()))
			return receptionistService.getPersonByUserId(id);

		if (role.equals(Roles.ROLE_DOCTOR.toString()))
			return doctorService.getPersonByUserId(id);

		return null;

//    	return null;
	}
}
