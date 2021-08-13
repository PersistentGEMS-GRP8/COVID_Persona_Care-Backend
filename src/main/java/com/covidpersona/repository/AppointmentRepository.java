package com.covidpersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.covidpersona.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query(value = "select * from covidapp.appointment a where a.p_id =:pid ", nativeQuery = true)
	public Appointment findByP_id(long pid);

//	@Query(value = "delete from covidapp.appointment where a_id =:appointmentNO;", nativeQuery = true)
	@Query(value = "Update covidapp.appointment a set a.appdate =:date, a.d_id=:did where a.p_id =:pid", nativeQuery = true)
	@Modifying
	@Transactional
	public void updateAppointment(long pid, String date, long did);

	@Query(value = "delete from covidapp.appointment where a_id =:appointmentNO", nativeQuery = true)
	@Modifying
	@Transactional
	public void deleteAppointment(long appointmentNO);

}
