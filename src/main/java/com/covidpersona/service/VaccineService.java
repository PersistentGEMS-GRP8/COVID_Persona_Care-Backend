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

	public Vaccine addVaccine(Vaccine vaccine) {
		return vaccineRepo.save(vaccine);
	}

	public List<Vaccine> getVaccines() {
		return vaccineRepo.findAll();
	}
}
