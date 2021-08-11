package com.covidpersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidpersona.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer>{

}
