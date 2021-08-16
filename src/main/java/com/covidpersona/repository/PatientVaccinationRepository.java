//Deepak
package com.covidpersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidpersona.entity.PatientVaccination;

public interface PatientVaccinationRepository extends JpaRepository<PatientVaccination, Integer>{

}
