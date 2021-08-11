package com.covidpersona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidpersona.entity.Receptionist;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {

	List<Receptionist> findByhId(int hId);
}
