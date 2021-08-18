package com.covidpersona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.dto.VaccineDto;
import com.covidpersona.entity.HospitalVaccine;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.HospitalVaccineRepository;

@Service
public class HospitalVaccineService {

	@Autowired
	private HospitalVaccineRepository hospVaccineRepo;
	
	public HospitalVaccine addVaccinesToHospital(HospitalVaccine vaccineToHosp) {
		HospitalVaccine savedVaccine= hospVaccineRepo.save(vaccineToHosp);
		return savedVaccine;
	}
	
	public List<VaccineDto> getAllVaccines(int hId){
		return hospVaccineRepo.findAllVaccinesInHospital(hId);
	}
	
	public VaccineDto getVaccineByIdAndHospId(long id,int hId) {
		return hospVaccineRepo.findVaccineById(id, hId);
	}
	
	public HospitalVaccine editVaccineInHospital(HospitalVaccine editedVaccine) {
		HospitalVaccine updatedVaccine= hospVaccineRepo.save(editedVaccine);
		return updatedVaccine;
	}
	
	public void deleteVaccine(long id) {
		hospVaccineRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hospital Vaccine", "id", id));
		hospVaccineRepo.deleteById(id);
	}
}
