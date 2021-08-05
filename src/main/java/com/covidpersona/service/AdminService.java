package com.covidpersona.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covidpersona.entity.Admin;
import com.covidpersona.exception.ResourceNotFoundException;
import com.covidpersona.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public Admin addAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	public Admin updateAdmin(Admin admin) {
		adminRepository.findById(admin.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Admin", "id", admin.getId()));
		return adminRepository.save(admin);
	}

	public void deleteAdmin(long id) {
		adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin", "id", id));
		adminRepository.deleteById(id);
	}

	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}
}
