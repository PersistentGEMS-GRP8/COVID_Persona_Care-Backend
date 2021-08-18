package com.covidpersona.repository;

import java.util.List;

import com.covidpersona.entity.Receptionist;

public interface ReceptionistRepository extends PersonRepository<Receptionist> {

	List<Receptionist> findByhId(int hId);
}
