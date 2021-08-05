package com.covidpersona.service;

import com.covidpersona.entity.HospitalAdmin;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.covidpersona.repository.HospitalAdminRepository;

@Service
public class HospitalAdminService {

	@Autowired
	private HospitalAdminRepository hospAdminRepository;

	public List<HospitalAdmin> getHospAdmins() {
		return hospAdminRepository.findAll();
	}

	public Optional<HospitalAdmin> getHospAdmin(long id) {
		return hospAdminRepository.findById(id);
	}

	public HospitalAdmin addHospAdmin(HospitalAdmin hospAdmin) {
		HospitalAdmin savedHospAdmin = hospAdminRepository.save(hospAdmin);
		return savedHospAdmin;
	}

	public HospitalAdmin updateHospAdmin(HospitalAdmin hospAdmin) {
		HospitalAdmin updatedHospAdmin = hospAdminRepository.save(hospAdmin);
		return updatedHospAdmin;
	}

	public void deleteHospAdmin(long id) {
		hospAdminRepository.deleteById(id);
	}
}
