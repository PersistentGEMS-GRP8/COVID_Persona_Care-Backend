package com.covidpersona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.covidpersona.entity.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long>{

	@Query(value = "SELECT * FROM Vaccine WHERE h_Id= ?1", nativeQuery = true)
	List<Vaccine> getVaccineByHId(int hId);
}
