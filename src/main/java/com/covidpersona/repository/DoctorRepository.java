package com.covidpersona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.covidpersona.dto.DoctorDto;
import com.covidpersona.entity.Doctor;

@Repository
public interface DoctorRepository extends PersonRepository<Doctor> {

	@Query("select new com.covidpersona.dto.DoctorDto(d.id, d.name, d.email, d.contactNo, s.id, s.name) from Doctor d, Specialization s where d.specialization = s")
	List<DoctorDto> findAllWithSpecialization();

	@Query("select new com.covidpersona.dto.DoctorDto(d.id, d.name, d.email, d.contactNo, s.id, s.name) from Doctor d, Specialization s where d.specialization = s and d.name like %:name%")
	List<DoctorDto> findAllWithSpecializationByNameLike(@Param("name") String name);

	@Query(name = "find_doctor_hospital", nativeQuery = true)
	List<DoctorDto> findAllWithSpecializationByHospital(@Param("hId") int hId, @Param("name") String name);

	@Query("select new com.covidpersona.dto.DoctorDto(d.id, d.name, d.email, d.contactNo, s.id, s.name) from Doctor d, Specialization s where d.specialization = s and d.id = :id")
	DoctorDto findDoctorWithSpecializationById(@Param("id") long id);

	List<Doctor> findBySpecialization_id(long id);

}