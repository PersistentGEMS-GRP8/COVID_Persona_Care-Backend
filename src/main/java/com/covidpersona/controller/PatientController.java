package com.covidpersona.controller;

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
import com.covidpersona.entity.Patient;
import com.covidpersona.service.PatientServiceImpl;
import com.covidpersona.service.auth.PersonaUserService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/patients")
public class PatientController {
	
	private final Logger log = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	private PersonaUserService personaUserService;
	
	@Autowired
	private PatientServiceImpl patientService;

	@PostMapping
	public long registerUserAccount(@RequestBody RegisterRequestDto registrationDto) {
		return personaUserService.RegisterPersonaUser(registrationDto.getPersonaUser(), registrationDto.getPerson());
	}
	
	@GetMapping
	public List<Patient> getPatientList(){
		return patientService.getPatientList();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPatient(@PathVariable long id) {
		Optional<Patient> patient = patientService.getPatientById(id);
		if (patient.isPresent()) {
			return ResponseEntity.ok(patient);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/add")
	public Patient addPatient(@RequestBody Patient patient)  {
		log.info("Request to create patient: {}", patient);
		return patientService.addPatient(patient);
	}
	
	@PutMapping
	public Patient updatePatient(@RequestBody Patient patient) {
		log.info("Request to update receptionist: {}", patient);
		return patientService.updatePatient(patient);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable int id) {
		log.info("Request to delete hospital: {}", id);
		patientService.deletePatient(id);
		return ResponseEntity.ok().build();
	}

}
