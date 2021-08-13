package com.covidpersona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidpersona.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{

	List<Patient> findByPatientId(int PatientId);
}
