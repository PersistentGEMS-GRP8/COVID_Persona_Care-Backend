package com.covidpersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.covidpersona.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

	@Query(value = "SELECT * FROM Hospital WHERE h_name= ?1", nativeQuery = true)
	Hospital findByName(String name);
}
