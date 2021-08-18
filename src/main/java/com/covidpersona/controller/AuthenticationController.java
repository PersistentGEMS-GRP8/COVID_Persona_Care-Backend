package com.covidpersona.controller;

import com.covidpersona.config.auth.JwtUtil;
import com.covidpersona.dto.RegisterRequestDto;
import com.covidpersona.entity.PersonaUser;
import com.covidpersona.exception.InvalidDataException;
import com.covidpersona.model.auth.AuthenticationRequest;
import com.covidpersona.model.auth.AuthenticationResponce;
import com.covidpersona.service.ProfileService;
import com.covidpersona.service.auth.CustomUserDetailsService;
import com.covidpersona.service.auth.PersonaUserService;

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
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PersonaUserService personaUserService;

	@Autowired
	private ProfileService profileService;

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
		String token = jwtUtil.generateToken(userDetails, user.getId());
		return ResponseEntity
				.ok(new AuthenticationResponce(token, profileService.getPersonData(user.getId(), user.getRole())));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public long createUser(@RequestBody RegisterRequestDto registerRequestDto) throws InvalidDataException {
		long id = personaUserService.RegisterPersonaUser(registerRequestDto.getPersonaUser(),
				registerRequestDto.getPerson());

		return id;
	}

}
