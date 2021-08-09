package com.covidpersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidpersona.entity.Receptionist;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {

}
