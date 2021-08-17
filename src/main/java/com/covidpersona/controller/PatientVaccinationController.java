//Deepak
package com.covidpersona.controller;

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

import com.covidpersona.entity.PatientVaccination;
import com.covidpersona.service.PatientVaccinationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/patientVaccination")
public class PatientVaccinationController {

	@Autowired
	private PatientVaccinationService patientVaccinationService;
	
	@PostMapping
	public ResponseEntity<PatientVaccination> addPatientVaccination(@RequestBody PatientVaccination patientVaccination){
		PatientVaccination savedVaccination = patientVaccinationService.addPatientVaccination(patientVaccination);
		return ResponseEntity.ok(savedVaccination);
	}
	
	@GetMapping
	public List<PatientVaccination> getPatientVaccinations() {
		return patientVaccinationService.getPatientVaccinations();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPatientVaccination(@PathVariable int id) {
		Optional<PatientVaccination> vaccination = patientVaccinationService.getPatientVaccination(id);
		if (vaccination.isPresent()) {
			return ResponseEntity.ok(vaccination);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping
	public ResponseEntity<PatientVaccination> updatePatientVaccination(@RequestBody PatientVaccination patientVaccination) {
		PatientVaccination updatedVaccination = patientVaccinationService.updatePatientVaccination(patientVaccination);
		return ResponseEntity.ok(updatedVaccination);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePatientVaccination(@PathVariable int id) {
		patientVaccinationService.deletePatientVaccination(id);;
		return ResponseEntity.ok().build();
	}
}
