package com.covidpersona.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.HospitalVaccine;
import com.covidpersona.repository.HospitalVaccineRepository;

@Service
public class HospitalVaccineService {

	@Autowired
	private HospitalVaccineRepository hospVaccineRepo;
	
	public HospitalVaccine addVaccinesToHospital(HospitalVaccine vaccineToHosp) {
		return hospVaccineRepo.save(vaccineToHosp);
	}
}
