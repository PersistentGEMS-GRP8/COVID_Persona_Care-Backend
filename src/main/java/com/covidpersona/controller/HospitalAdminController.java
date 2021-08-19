package com.covidpersona.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.dto.RegisterRequestDto;
import com.covidpersona.entity.HospitalAdmin;
import com.covidpersona.service.HospitalAdminService;
import com.covidpersona.service.auth.PersonaUserService;

import lombok.AllArgsConstructor;


@CrossOrigin(origins={ "http://localhost:3000" })
@RestController
@RequestMapping("/hospitalAdmins")
@AllArgsConstructor
public class HospitalAdminController {
	
	private final Logger log = LoggerFactory.getLogger(HospitalAdminController.class);

	@Autowired
	private final HospitalAdminService hospAdminService;
	private final PersonaUserService personaUserService;


	@GetMapping
	public List<HospitalAdmin> getHospAdmins() {
		log.info("Request to get hospital admins");
		return hospAdminService.getHospAdmins();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getHospAdmin(@PathVariable long id) {
		log.info("Request to get hospital admin by id "+id);
		Optional<HospitalAdmin> hospAdmin = hospAdminService.getHospAdmin(id);
		if (hospAdmin.isPresent()) {
			return ResponseEntity.ok(hospAdmin);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping
	public long addHospAdmin(@RequestBody RegisterRequestDto hospAdmin) throws URISyntaxException {
		log.info("Request to add hospital admin "+hospAdmin);
		HospitalAdmin hAdmin = (HospitalAdmin) hospAdmin.getPerson();
		return personaUserService.RegisterPersonaUser(hospAdmin.getPersonaUser(),hAdmin);
	}

	@PutMapping("/{id}")
	public ResponseEntity<HospitalAdmin> updateHospAdmin(@RequestBody HospitalAdmin hospAdmin) {
		log.info("Request to update hospital admin "+hospAdmin);
		HospitalAdmin updatedHospAdmin = hospAdminService.updateHospAdmin(hospAdmin);
		return ResponseEntity.ok(updatedHospAdmin);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteHospAdmin(@PathVariable long id) {
		log.info("Request to delete hospital admin "+id);
		hospAdminService.deleteHospAdmin(id);
		return ResponseEntity.ok().build();
	}
}
