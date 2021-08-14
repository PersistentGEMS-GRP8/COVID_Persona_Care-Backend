package com.covidpersona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.covidpersona.entity.Vaccine;
import com.covidpersona.repository.VaccineRepository;

@Service
public class VaccineService {

	@Autowired
	private VaccineRepository vaccineRepo;
	
//	public List<Vaccine> getVaccineByHospId(int id){
//		return vaccineRepo.getVaccineByHId(id);
//	}
	
	public List<Vaccine> getVaccines(){
		return vaccineRepo.findAll();
	}
}
