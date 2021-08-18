package com.covidpersona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.covidpersona.dto.SpecializationDto;
import com.covidpersona.entity.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
	List<Specialization> findAllByOrderByIdAsc();

	List<Specialization> findByNameContaining(String name);

	Specialization findByName(String name);

	@Query("select new com.covidpersona.dto.SpecializationDto(s.id, s.name, count(d.id)) from Specialization s left join s.doctors d where s.name like  %?1% group by s.name order by s.id")
	List<SpecializationDto> findAllWithDoctorCount(@Param("name") String name);
}
