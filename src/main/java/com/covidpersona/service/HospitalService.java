package com.covidpersona.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Hospital;
import com.covidpersona.exception.InvalidDataException;
import com.covidpersona.repository.HospitalRepository;

@Service
public class HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	public List<Hospital> getHospitals() {
		return hospitalRepository.findAll();
	}

	public Optional<Hospital> getHospital(int id) {
		return hospitalRepository.findById(id);
	}

	public Hospital addHospital(Hospital hospital) {
		Hospital oldHospital = hospitalRepository.findHospitalByHName(hospital.gethName());
		if (oldHospital != null) {
			throw new InvalidDataException("Hospital already exists");
		}
		Hospital savedHospital = hospitalRepository.save(hospital);
		return savedHospital;
	}

	public Hospital updateHospital(Hospital hospital) {
		Hospital existHospital = getHospital(hospital.gethId()).get();
        existHospital.sethName(hospital.gethName());
        existHospital.setLocation(hospital.getLocation());
        existHospital.setNoOfBeds(hospital.getNoOfBeds());
        return hospitalRepository.save(existHospital);
	}

	public void deleteHospital(int id) {
		hospitalRepository.deleteById(id);
	}

	public Hospital getHospitalByName(String name) {
		return hospitalRepository.findByName(name);
	}

	public Hospital updateBeds(int hId, int beds) {
		Hospital previous = hospitalRepository.findById(hId).get();
		previous.setNoOfBeds(beds);
		Hospital updatedHospital = hospitalRepository.save(previous);
		return updatedHospital;
	}

}
