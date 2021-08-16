package com.covidpersona.service;

import com.covidpersona.entity.HospitalAdmin;
import com.covidpersona.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.covidpersona.repository.HospitalAdminRepository;

@Service
public class HospitalAdminService extends PersonService<HospitalAdmin> {

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
	        Optional<HospitalAdmin> existHAdmin = getHospAdmin(hospAdmin.getId());
	        hospAdmin.setUserId(existHAdmin.get().getUserId());
	        HospitalAdmin updatedHospAdmin = hospAdminRepository.save(hospAdmin);
	        return updatedHospAdmin;
	}

	public void deleteHospAdmin(long id) {
		hospAdminRepository.deleteById(id);
	}

	@Override
	public HospitalAdmin getPersonByUserId(long id) {
		return hospAdminRepository.findByUserId_Id(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hospital Admin", "user_id", id));
	}
}
