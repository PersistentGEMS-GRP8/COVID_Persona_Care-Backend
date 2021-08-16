package com.covidpersona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.dto.VaccineDto;
import com.covidpersona.entity.HospitalVaccine;
import com.covidpersona.repository.HospitalVaccineRepository;

@Service
public class HospitalVaccineService {

	@Autowired
	private HospitalVaccineRepository hospVaccineRepo;
	
	public HospitalVaccine addVaccinesToHospital(HospitalVaccine vaccineToHosp) {
		return hospVaccineRepo.save(vaccineToHosp);
	}
	
	public List<VaccineDto> getAllVaccines(int hId){
		return hospVaccineRepo.findAllVaccinesInHospital(hId);
	}
	
	public VaccineDto getVaccineByIdAndHospId(long id,int hId) {
		return hospVaccineRepo.findVaccineById(id, hId);
	}
	
	public HospitalVaccine editVaccineInHospital(HospitalVaccine editedVaccine) {
		return hospVaccineRepo.save(editedVaccine);
	}
	
	public void deleteVaccine(long id) {
		hospVaccineRepo.deleteById(id);
	}
}
