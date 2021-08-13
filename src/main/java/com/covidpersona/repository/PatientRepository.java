package com.covidpersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.covidpersona.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	public Patient findByEmail(String username);

	@Query(value = "update covidapp.patient p set p.appointment_no=:napp_id where p.id=:new_id", nativeQuery = true)
	@Modifying
	@Transactional
	public void findById(long napp_id, long new_id);

}
