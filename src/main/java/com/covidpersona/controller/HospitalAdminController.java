package com.covidpersona.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

import com.covidpersona.entity.HospitalAdmin;
import com.covidpersona.service.HospitalAdminService;

@CrossOrigin(origins={ "http://localhost:3000" })
@RestController
@RequestMapping("/hospitalAdmins")
public class HospitalAdminController {

	@Autowired
	private HospitalAdminService hospAdminService;

	@GetMapping
	public List<HospitalAdmin> getHospAdmins() {
		return hospAdminService.getHospAdmins();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getHospAdmin(@PathVariable long id) {
		Optional<HospitalAdmin> hospAdmin = hospAdminService.getHospAdmin(id);
		if (hospAdmin.isPresent()) {
			return ResponseEntity.ok(hospAdmin);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<HospitalAdmin> addHospAdmin(@RequestBody HospitalAdmin hospAdmin) throws URISyntaxException {
		HospitalAdmin savedHospAdmin = hospAdminService.addHospAdmin(hospAdmin);
		return ResponseEntity.created(new URI("/hospitalAdmins/" + savedHospAdmin.getId())).body(savedHospAdmin);
	}

	@PutMapping
	public ResponseEntity<HospitalAdmin> updateHospAdmin(@RequestBody HospitalAdmin hospAdmin) {
		HospitalAdmin updatedHospAdmin = hospAdminService.updateHospAdmin(hospAdmin);
		return ResponseEntity.ok(updatedHospAdmin);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteHospAdmin(@PathVariable long id) {
		hospAdminService.deleteHospAdmin(id);
		return ResponseEntity.ok().build();
	}
}
