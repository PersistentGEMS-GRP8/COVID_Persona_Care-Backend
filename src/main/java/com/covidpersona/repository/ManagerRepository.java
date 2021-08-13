package com.covidpersona.repository;

import java.util.List;

import com.covidpersona.entity.Manager;

public interface ManagerRepository extends PersonRepository<Manager> {

	List<Manager> findByhId(int hId);
}
