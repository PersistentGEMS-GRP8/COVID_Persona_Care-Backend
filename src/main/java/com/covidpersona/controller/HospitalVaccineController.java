package com.covidpersona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.entity.HospitalVaccine;
import com.covidpersona.service.HospitalVaccineService;

@RestController
@RequestMapping("/vaccinesInHospital")
@CrossOrigin("http://localhost:3000")
public class HospitalVaccineController {

	@Autowired
	private HospitalVaccineService hospVaccineService;
	
	@PostMapping
	public HospitalVaccine addVaccineToHospital(@PathVariable long count, @RequestBody HospitalVaccine vaccineToHospital) {
		System.out.println(vaccineToHospital);
		System.out.println(count);
		return hospVaccineService.addVaccinesToHospital(vaccineToHospital);
	}
}
