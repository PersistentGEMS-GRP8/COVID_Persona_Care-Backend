package com.covidpersona.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidpersona.entity.Admin;
import com.covidpersona.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	/*
	 * Manage Admin
	 */

	@PostMapping
	public Admin addAdmin(@Valid @RequestBody Admin admin) {
		return adminService.addAdmin(admin);
	}

	@PutMapping
	public Admin updateManager(@Valid @RequestBody Admin admin) {
		return adminService.updateAdmin(admin);
	}

	@DeleteMapping("/{id}")
	public void deleteAdmin(@PathVariable long id) {
		adminService.deleteAdmin(id);
	}

	@GetMapping
	public List<Admin> getAllAdmin() {
		return adminService.getAllAdmin();
	}
	
	@GetMapping("/{id}")
	public Admin getAdmin(@PathVariable long id) {
		return adminService.getAdmin(id);
	}

	/*
	 * Manage Hospitals
	 */

	@PostMapping("/hospitals")
	public void registerHospital() {

	}

	@GetMapping("/hospitals")
	public List<String> getAllHospitals() {
		return Arrays.asList("Hospital 1", "Hospital 2");
	}

	@GetMapping("/hospitals/{id}")
	public String getHospitalById(@PathVariable long id) {
		return "Hospital 1";
	}

	@PutMapping("/hospitals")
	public void updateHospital() {

	}

	@DeleteMapping("/hospitals/{id}")
	public void deleteHospital(@PathVariable("id") long hospitalId) {

	}

	/*
	 * Manage Hospitals Admin
	 */

	@PostMapping("/hospital/{hid}/admin")
	public String registerHospitalAdmin(@PathVariable("hid") long hospitalId) {
		return "Hospital_ID: " + hospitalId;
	}

	@PutMapping("/hospital/admin")
	public void updateHospitalAdmin() {

	}

	@DeleteMapping("hospital/admin/{id}")
	public void deleteHospitalAdmin(@PathVariable("id") long hospitalAdminId) {

	}

	public void getHospitalAdmin() {

	}

}
