package com.covidpersona.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.entity.Patient;
import com.covidpersona.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@PostMapping
	public Patient addPatient(@Valid @RequestBody Patient patient) {
		return patientService.addPatient(patient);
	}

	@PutMapping
	public Patient updatePatient(@Valid @RequestBody Patient patient) {
		return patientService.updatePatient(patient);
	}

	@DeleteMapping("/{id}")
	public void deletePatient(@PathVariable long id) {
		patientService.deletePatient(id);
	}

	@GetMapping
	public List<Patient> getAllPatient() {
		return patientService.getAllPatient();
	}

	@GetMapping("/{id}")
	public Patient getPatient(@PathVariable long id) {
		return patientService.getPatient(id);
	}

}

//Add List of Patient to Receptionist (also in Admin if possible )