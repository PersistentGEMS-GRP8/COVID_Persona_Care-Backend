package com.covidpersona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
//	@GetMapping
//	public List<Vaccine> getVaccineByHosId(@RequestParam int hId){
//		return vaccineService.getVaccineByHospId(hId);
//	}

	@GetMapping
	public List<Vaccine> getVaccines(){
		return vaccineService.getVaccines();
	}
}
