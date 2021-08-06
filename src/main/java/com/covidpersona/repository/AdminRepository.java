package com.covidpersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.covidpersona.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
