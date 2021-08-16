//Deepak
package com.covidpersona.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.entity.PatientVaccination;
import com.covidpersona.service.PatientVaccinationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/patientVaccination")
public class PatientVaccinationController {

	@Autowired
	private PatientVaccinationService patientVaccinationService;
	
	@PostMapping
	public ResponseEntity<PatientVaccination> addPatientVaccination(@RequestBody PatientVaccination patientVaccination) throws URISyntaxException {
		PatientVaccination savedVaccination = patientVaccinationService.addPatientVaccination(patientVaccination);
		return ResponseEntity.ok(savedVaccination);
	}
	
}
