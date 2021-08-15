package com.covidpersona.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.covidpersona.entity.Person;

@NoRepositoryBean
public interface PersonRepository<T extends Person> extends JpaRepository<T, Long> {
	Optional<T> findByUserId_Id(Long id);
	T findByEmail(String email);
}
