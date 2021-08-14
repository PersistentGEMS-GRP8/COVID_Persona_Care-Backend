package com.covidpersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidpersona.entity.HospitalVaccine;

public interface HospitalVaccineRepository extends JpaRepository<HospitalVaccine, Long>{

}
