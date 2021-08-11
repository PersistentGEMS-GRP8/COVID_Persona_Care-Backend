package com.covidpersona.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.entity.Specialization;
import com.covidpersona.service.SpecializationService;

@RestController
@RequestMapping("/specializations")
public class SpecializationController {
	
	@Autowired
	private SpecializationService specializationService;
	
	@PostMapping
	public Specialization addSpecialization(@RequestBody @Valid Specialization specialization) {
		return specializationService.addSpecialization(specialization);
	}
	
	@GetMapping
	public List<Specialization> getAllSpecialization() {
		return specializationService.getAllSpecialization();
	}
	
	@GetMapping("/{id}")
	public Specialization getSpecialization(@PathVariable int id) {
		return specializationService.getSpecialization(id);
	}
}
