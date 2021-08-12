package com.covidpersona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidpersona.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long>{

	List<Manager> findByhId(int hId);
}


