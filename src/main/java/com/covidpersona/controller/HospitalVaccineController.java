package com.covidpersona.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.covidpersona.dto.VaccineDto;
import com.covidpersona.entity.HospitalVaccine;
import com.covidpersona.service.HospitalVaccineService;

@RestController
@RequestMapping("/vaccinesInHospital")
@CrossOrigin("http://localhost:3000")
public class HospitalVaccineController {

	private final Logger log = LoggerFactory.getLogger(HospitalVaccineController.class);
	
	@Autowired
	private HospitalVaccineService hospVaccineService;
	
	@GetMapping("/{hId}")
	public List<VaccineDto> getAllVaccinesInHospital(@PathVariable int hId){
		log.info("Request to get Vaccines by hospitalId "+hId);
		return hospVaccineService.getAllVaccines(hId);
	}
	
	@GetMapping("/getVaccine/{id}/{hId}")
	public VaccineDto getVaccineByIdAndHospId(@PathVariable long id,@PathVariable int hId) {
		log.info("Request to get Vaccines by hospitalId "+hId+" and vaccine id "+id);
		return hospVaccineService.getVaccineByIdAndHospId(id, hId);
	}
	
	@PostMapping
	public ResponseEntity<HospitalVaccine> addVaccineToHospital(@Valid @RequestBody HospitalVaccine vaccineToHospital) {
		log.info("Request to add vaccine into hospital "+vaccineToHospital);
		HospitalVaccine saveVaccine= hospVaccineService.addVaccinesToHospital(vaccineToHospital);
		return ResponseEntity.ok(saveVaccine);
	}
	
	@PutMapping
	public HospitalVaccine editVaccineInHospital(@Valid @RequestBody HospitalVaccine updatedVaccine) {
		log.info("Request to update vaccine in hospital "+updatedVaccine);
		return hospVaccineService.editVaccineInHospital(updatedVaccine);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVaccine(@PathVariable long id) {
		log.info("Request to delete vaccine in hospital "+id);
		hospVaccineService.deleteVaccine(id);
		return ResponseEntity.ok().build();
	}

}
