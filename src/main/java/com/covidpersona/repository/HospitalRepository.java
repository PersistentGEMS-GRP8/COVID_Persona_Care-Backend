package com.covidpersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.covidpersona.entity.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer>{
	
	@Query("SELECT p FROM Hospital p WHERE LOWER(p.hName) = LOWER(:hName)")
	Hospital findHospitalByHName(@Param("hName") String hName);

	@Query(value = "SELECT * FROM Hospital WHERE h_name= ?1", nativeQuery = true)
	Hospital findByName(String name);
}
