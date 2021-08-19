package com.covidpersona.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.entity.Hospital;
import com.covidpersona.service.HospitalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins={ "http://localhost:3000" })
@RestController
@RequestMapping("/hospitals")
public class HospitalController {
	
	private final Logger log = LoggerFactory.getLogger(HospitalController.class);
	
	@Autowired
	private HospitalService hospitalService;

	@GetMapping
	public List<Hospital> getHospitals() {
		return hospitalService.getHospitals();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getHospital(@PathVariable int id) {
		Optional<Hospital> hospital = hospitalService.getHospital(id);
		if (hospital.isPresent()) {
			return ResponseEntity.ok(hospital);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Hospital> addHospital(@RequestBody Hospital hospital) throws URISyntaxException {
		log.info("Request to create hospital: {}", hospital);
		Hospital savedHospital =hospitalService.addHospital(hospital);
		return ResponseEntity.ok(savedHospital);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Hospital> updateHospital(@RequestBody Hospital hospital) {
		log.info("Request to update hospital: {}", hospital);
		Hospital updatedHospital = hospitalService.updateHospital(hospital);
		return ResponseEntity.ok(updatedHospital);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteHospital(@PathVariable int id) {
		log.info("Request to delete hospital: {}", id);
		hospitalService.deleteHospital(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/search")
	public Hospital getHospitalsByName(@RequestParam String name) {
		log.info("Request to get hospital by name: {}", name);
		return hospitalService.getHospitalByName(name);
	
	}
	@PutMapping("/manage_beds")
	public ResponseEntity<Hospital> manageBeds(@RequestParam int hId,@RequestParam int beds) {
		log.info("Request to update hospitalbeds: {}", hId, beds);
		Hospital updatedHospital = hospitalService.updateBeds(hId,beds);
		return ResponseEntity.ok(updatedHospital);
	}
}
