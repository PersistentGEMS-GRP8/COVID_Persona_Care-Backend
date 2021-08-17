package com.covidpersona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.covidpersona.dto.VaccineDto;
import com.covidpersona.entity.HospitalVaccine;

public interface HospitalVaccineRepository extends JpaRepository<HospitalVaccine, Long>{

	@Query("select new com.covidpersona.dto.VaccineDto( hv.id as id,v.id as vaccineId, v.name,hv.count) from HospitalVaccine hv, Vaccine v where hv.vaccineId=v.id and hv.hospitalId=?1")
	List<VaccineDto> findAllVaccinesInHospital(int hId);

	@Query("select new com.covidpersona.dto.VaccineDto(hv.id as id,v.id as vaccineId,  v.name,hv.count) from HospitalVaccine hv, Vaccine v where hv.vaccineId=v.id and hv.id=?1 and hv.hospitalId=?2")
	VaccineDto findVaccineById(long id,int hId);
}
