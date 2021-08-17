package com.covidpersona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Admin;
import com.covidpersona.exception.InvalidDataException;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.AdminRepository;

@Service
public class AdminService extends PersonService<Admin> {

	@Autowired
	private AdminRepository adminRepository;

	public long addAdmin(Admin admin) {
		Admin existAdmin = adminRepository.findByEmail(admin.getEmail());
		if (existAdmin != null)
			throw new InvalidDataException("Email already exists");
		return adminRepository.save(admin).getId();
	}

	public Admin updateAdmin(Admin admin) {
		Admin existAdmin = getAdmin(admin.getId());
		admin.setUserId(existAdmin.getUserId());
		return adminRepository.save(admin);
	}

	public void deleteAdmin(long id) {
		getAdmin(id);
		adminRepository.deleteById(id);
	}

	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}

	public Admin getAdmin(long id) {
		Admin admin = adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin", "id", id));
		return admin;
	}

	@Override
	public Admin getPersonByUserId(long id) {
		return adminRepository.findByUserId_Id(id)
				.orElseThrow(() -> new ResourceNotFoundException("Admin", "user_id", id));
	}
}
