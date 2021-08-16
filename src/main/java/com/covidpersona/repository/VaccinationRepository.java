package com.covidpersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.VaccinationDetails.domain.Patient;

@Repository
public interface VaccinationRepository extends JpaRepository<Patient, Long>{

}
