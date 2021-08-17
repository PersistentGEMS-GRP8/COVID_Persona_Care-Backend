package com.covidpersona.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.entity.Vaccine;
import com.covidpersona.service.VaccineService;


@RestController
@RequestMapping("/vaccines")
@CrossOrigin("http://localhost:3000")
public class VaccineController {
	
	@Autowired
	private VaccineService vaccineService;
	

	@GetMapping
	public List<Vaccine> getVaccines(){
		return vaccineService.getVaccines();
	}
	
	@PostMapping
	public Vaccine addVaccine(@Valid @RequestBody Vaccine vaccine) {
		return vaccineService.addVaccine(vaccine);
	}
	
}
